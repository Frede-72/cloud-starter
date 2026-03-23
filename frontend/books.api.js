// const BASE_URL = "http://localhost:8080"; // Adjust this if your backend is running on a different URL or port
const BASE_URL = ""; // Adjust this if your backend is running on a different URL or port

const API_URL = `${BASE_URL}/api/books`;

export async function fetchBooks() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Error fetching books:", error);
        throw error;
    }
}

export async function fetchBookById(id) {
    const response = await fetch(`${API_URL}/${id}`);
    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
    return await response.json();
}

export async function addBook(book) {
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(book),
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Error adding book:", error);
        throw error;
    }
}