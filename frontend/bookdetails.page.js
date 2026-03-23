import { fetchBookById } from "./books.api.js";

export async function bookDetailsPage(root, bookId) {

    try {
        const data = await fetchBookById(bookId);
        root.innerHTML = `
        <div class="container mt-5">
            <h1>Book Details</h1>
            <p><strong>Title:</strong> ${data.title}</p>
            <p><strong>ISBN:</strong> ${data.isbn}</p>
        </div>
    `;
    } catch (error) {
        console.error(`Error fetching book with ID ${bookId}:`, error);
        root.innerHTML = `
            <div class="container mt-5">
                <h1>Book not found</h1>
                <p>The book with ID ${bookId} could not be found.</p>
            </div>
        `;
    }
}