const contentDiv = document.getElementById("content");
const sheltersBtn = document.getElementById("viewSheltersBtn");
const dogsBtn = document.getElementById("viewDogsBtn");

// ✅ Fetch all shelters
async function loadShelters() {
    const res = await fetch("/api/shelter");
    const shelters = await res.json();

    contentDiv.innerHTML = "<h2>All Shelters</h2>";

    shelters.forEach(shelter => {
        const shelterCard = document.createElement("div");
        shelterCard.className = "card";
        shelterCard.innerHTML = `
            <h3>${shelter.name}</h3>
            <p><strong>Location:</strong> ${shelter.location}</p>
            <p><strong>Email:</strong> ${shelter.email || "N/A"}</p>
            <p><strong>Phone:</strong> ${shelter.phoneNumber || "N/A"}</p>
            <button class="details-btn" onclick="loadShelterDogs(${shelter.id})">View Dogs</button>
        `;
        contentDiv.appendChild(shelterCard);
    });
}

// ✅ Fetch single shelter & show dogs
async function loadShelterDogs(shelterId) {
    const res = await fetch(`/api/shelter/${shelterId}`);
    const shelter = await res.json();

    contentDiv.innerHTML = `
        <h2>Dogs in ${shelter.name}</h2>
        <p><strong>Location:</strong> ${shelter.location}</p>
        <p><strong>Email:</strong> ${shelter.email || "N/A"}</p>
        <p><strong>Phone:</strong> ${shelter.phoneNumber || "N/A"}</p>
        <button onclick="loadShelters()">⬅ Back to Shelters</button>
        <div id="dogsList"></div>
    `;

    const dogsListDiv = document.getElementById("dogsList");

    if (shelter.dogs && shelter.dogs.length > 0) {
        shelter.dogs.forEach(dog => {
            const dogCard = document.createElement("div");
            dogCard.className = "card";
            dogCard.innerHTML = `
                <h4>${dog.name}</h4>
                <p><strong>Breed:</strong> ${dog.breed}</p>
                <p><strong>Age:</strong> ${dog.age}</p>
                <p><strong>Gender:</strong> ${dog.gender}</p>
                <p>${dog.description || ""}</p>
            `;
            dogsListDiv.appendChild(dogCard);
        });
    } else {
        dogsListDiv.innerHTML = "<p>No dogs in this shelter yet.</p>";
    }
}

// ✅ Fetch all dogs
async function loadDogs() {
    const res = await fetch("/api/dog");
    const dogs = await res.json();

    contentDiv.innerHTML = "<h2>All Dogs</h2>";

    dogs.forEach(dog => {
        const dogCard = document.createElement("div");
        dogCard.className = "card";
        dogCard.innerHTML = `
            <h3>${dog.name}</h3>
            <p><strong>Breed:</strong> ${dog.breed}</p>
            <p><strong>Age:</strong> ${dog.age}</p>
            <p><strong>Gender:</strong> ${dog.gender}</p>
            <p><strong>Description:</strong> ${dog.description || "N/A"}</p>
            <p><em>Shelter:</em> ${dog.shelter ? dog.shelter.name : "Unknown"}</p>
        `;
        contentDiv.appendChild(dogCard);
    });
}

// ✅ Hook up buttons
sheltersBtn.addEventListener("click", loadShelters);
dogsBtn.addEventListener("click", loadDogs);
