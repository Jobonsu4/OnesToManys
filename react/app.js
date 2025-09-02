const e = React.createElement;

function App() {
  const [view, setView] = React.useState('main'); // main, shelterList, dogList, dogProfile
  const [shelters, setShelters] = React.useState([]);
  const [dogs, setDogs] = React.useState([]);
  const [currentShelter, setCurrentShelter] = React.useState(null);
  const [currentDog, setCurrentDog] = React.useState(null);
  const [newShelter, setNewShelter] = React.useState({ name: '', location: '', email: '', phoneNumber: '' });
  const [newDog, setNewDog] = React.useState({ name: '', breed: '', age: '', gender: '', description: '', shelterId: '', imageUrl: '' });

  // ------------------ READ ------------------
  function loadShelters() {
    fetch("http://localhost:8080/api/shelter")
      .then(res => res.json())
      .then(data => { setShelters(data); setView('shelterList'); })
      .catch(err => console.error(err));
  }

  function loadDogs(shelterId) {
    fetch(`http://localhost:8080/api/shelter/${shelterId}`)
      .then(res => res.json())
      .then(data => {
        setCurrentShelter(data);
        setDogs(data.dogs);
        setView('dogList');
      })
      .catch(err => console.error(err));
  }

  function viewDog(dogId) {
    fetch(`http://localhost:8080/api/dog/${dogId}`)
      .then(res => res.json())
      .then(data => { setCurrentDog(data); setView('dogProfile'); })
      .catch(err => console.error(err));
  }

  // ------------------ CREATE ------------------
  function addShelter() {
    fetch("http://localhost:8080/api/shelter", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newShelter)
    })
    .then(res => res.json())
    .then(() => { 
      setNewShelter({ name: '', location: '', email: '', phoneNumber: '' });
      loadShelters();
    })
    .catch(err => console.error(err));
  }

  function addDog() {
    const shelterId = newDog.shelterId;
    const payload = { ...newDog };
    delete payload.shelterId;

    fetch(`http://localhost:8080/api/dog/shelter/${shelterId}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    })
    .then(res => res.json())
    .then(() => { 
      setNewDog({ name: '', breed: '', age: '', gender: '', description: '', shelterId: '', imageUrl: '' });
      loadDogs(shelterId);
    })
    .catch(err => console.error(err));
  }

  // ------------------ DELETE ------------------
  function deleteShelter(id) {
    if (!confirm("Delete this shelter?")) return;
    fetch(`http://localhost:8080/api/shelter/${id}`, { method: "DELETE" })
      .then(() => loadShelters())
      .catch(err => console.error(err));
  }

  function deleteDog(id) {
    if (!confirm("Delete this dog?")) return;
    fetch(`http://localhost:8080/api/dog/${id}`, { method: "DELETE" })
      .then(() => loadDogs(currentShelter.id))
      .catch(err => console.error(err));
  }

  // ------------------ RENDER ------------------
  if (view === 'main') {
    return e('div', { className: 'card' }, [
      e('h2', { key: 'h2' }, 'Welcome to Dog Shelter Management 🐾'),
      e('button', { onClick: loadShelters, className: 'primary-btn', key: 'btn1' }, 'View Shelters')
    ]);
  }

  if (view === 'shelterList') {
    return e('div', { className: 'card' }, [
      e('h2', { key: 'h2' }, 'All Shelters'),
      e('ul', { className: 'list', key: 'ul' }, shelters.map(shelter =>
        e('li', { key: shelter.id, className: 'list-item' }, [
          e('div', { className: 'shelter-info', key: 'info' }, 
            `${shelter.name} - ${shelter.location} | ${shelter.email} | ${shelter.phoneNumber}`
          ),
          e('div', { key: 'btns' }, [
            e('button', { onClick: () => loadDogs(shelter.id), className: 'primary-btn', key: 'view' }, 'View Dogs'),
            e('button', { onClick: () => deleteShelter(shelter.id), className: 'secondary-btn', key: 'del' }, 'Delete')
          ])
        ])
      )),
      e('button', { onClick: () => setView('main'), className: 'secondary-btn', key: 'back' }, '⬅ Back')
    ]);
  }

  if (view === 'dogList') {
    return e('div', { className: 'card' }, [
      e('h2', { key: 'h2' }, `Dogs in ${currentShelter.name}`),
      e('ul', { className: 'list', key: 'ul' }, dogs.map(dog =>
        e('li', { key: dog.id, className: 'list-item' }, [
          e('div', { key: 'dogInfo' }, `${dog.name} (${dog.breed})`),
          e('div', { key: 'dogBtns' }, [
            e('button', { onClick: () => viewDog(dog.id), className: 'primary-btn', key: 'viewDog' }, 'View'),
            e('button', { onClick: () => deleteDog(dog.id), className: 'secondary-btn', key: 'delDog' }, 'Delete')
          ])
        ])
      )),
      e('button', { onClick: () => loadShelters(), className: 'secondary-btn', key: 'backShelters' }, '⬅ Back to Shelters')
    ]);
  }

 if (view === 'dogProfile') {
  return e('div', { className: 'card' }, [
    e('h2', { key: 'h2' }, `${currentDog.name} 🐕`),
    e('p', { key: 'breed' }, `Breed: ${currentDog.breed}`),
    e('p', { key: 'age' }, `Age: ${currentDog.age}`),
    e('p', { key: 'gender' }, `Gender: ${currentDog.gender}`),
    e('p', { key: 'desc' }, `Description: ${currentDog.description}`),
    e('button', 
      { onClick: () => loadDogs(currentShelter.id), className: 'secondary-btn', key: 'backDogs' }, 
      '⬅ Back to Dogs'
    )
  ]);
}
  return null;
}

// Mount the React app
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(e(App));
