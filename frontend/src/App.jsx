import "./App.css";
import { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8081/items")
      .then((response) => {
        setTodos(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const addTodo = () => {
    axios
      .post("http://localhost:8081/items", {
        title: title,
        completed: false,
      })
      .then((response) => {
        setTodos([...todos, response.data]);
        setTitle("");
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const deleteTodo = (id) => {
    axios
      .delete(`http://localhost:8081/items/${id}`)
      .then(() => {
        setTodos(todos.filter((todo) => todo.id !== id));
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const toggleComplete = (todo) => {
    axios
      .put(`http://localhost:8081/items/${todo.id}`, {
        title: todo.title,
        completed: !todo.completed,
      })
      .then((response) => {
        setTodos(
          todos.map((t) =>
            t.id === todo.id ? response.data : t
          )
        );
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div className="container">
      <h1>Todo List</h1>

      <div className="input-section">
        <input
          type="text"
          placeholder="Enter Todo"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <button onClick={addTodo}>Add Todo</button>
      </div>

      {todos.map((todo) => (
        <div key={todo.id} className="todo-card">
          <h3>{todo.title}</h3>

          <p>
            {todo.completed ? "Completed ✅" : "Not Completed ❌"}
          </p>

          <div className="buttons">
            <button onClick={() => toggleComplete(todo)}>
              {todo.completed ? "Mark Pending" : "Mark Complete"}
            </button>

            <button onClick={() => deleteTodo(todo.id)}>
              Delete
            </button>
          </div>
        </div>  
      ))}
    </div>
  );
}

export default App;