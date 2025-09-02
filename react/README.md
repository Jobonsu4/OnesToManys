  ┌───────────────────────┐
  │   React Library       │
  │ (react.development.js)│
  │ - Lets you create     │
  │   components          │
  │ - Manage state        │
  └─────────┬─────────────┘
            │
            ▼
  ┌─────────────────────────┐
  │ ReactDOM Library         │
  │ (react-dom.development.js) │
  │ - Renders components      │
  │   into the page           │
  │ - Connects React to <div> │
  └─────────┬─────────────────┘
            │
            ▼
  ┌───────────────────────┐
  │ Your App Code         │
  │ (app.js)              │
  │ - Your App() function │
  │ - Your buttons, state │
  │ - Fetch API calls     │
  └─────────┬─────────────┘
            │
            ▼
  ┌───────────────────────┐
  │ HTML Page (<div id="root">) │
  │ - Where ReactDOM inserts  │
  │   your app UI             │
  └───────────────────────────┘
Flow in words:

React library gives you tools to write components and manage state.

ReactDOM takes your React components and puts them into the HTML page.

Your app code (app.js) defines your UI, buttons, and logic.

ReactDOM renders your app inside <div id="root">.