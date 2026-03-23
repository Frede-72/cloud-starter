import { fetchBooks, addBook } from "./books.api.js";
import router from "./router.js";

export async function booksPage(root) {
    root.innerHTML = "";
    // Create table structure
    const table = createTable();
    root.appendChild(table);
    const tbody = table.querySelector("tbody");

    // Fetch and display books
    const books = await fetchBooks();
    books.forEach(book => {
        const row = createRow(book);
        tbody.appendChild(row);
    });

    tbody.addEventListener("click", handleTableClick);

}

function handleTableClick(event) {
    const row = event.target.closest("tr");
    if (row) {
        const bookId = row.getAttribute("data-id");
        router.navigate(`/books/${bookId}`);
    }
}

function createTable() {
    const table = document.createElement("table");
    table.className = "table table-striped";
    const thead = document.createElement("thead");
    thead.innerHTML = `
        <tr>
            <th>Title</th>
            <th>ISBN</th>
        </tr>
    `;
    table.appendChild(thead);
    const tbody = document.createElement("tbody");
    tbody.setAttribute("id", "todoTableBody");
    table.appendChild(tbody);
    return table;
}

function createRow(book) {
    const row = document.createElement("tr");
    row.setAttribute("data-id", book.id);

    const title = document.createElement("td");
    title.textContent = book.title;
    row.appendChild(title);

    const isbn = document.createElement("td");
    isbn.textContent = book.isbn;
    row.appendChild(isbn);

    return row;
}
