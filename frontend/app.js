import { bookDetailsPage } from "./bookdetails.page.js";
import { booksPage } from "./books.page.js";
import router from "./router.js";

const app = document.querySelector('#app');

function renderHomePage() {
    app.innerHTML = `
       <div class="container mt-5">
            <h1 class="mb-4">Sample Application</h1>
        </div>
    `;
}

function renderBooksPage() {
    booksPage(app);
}

function renderBookDetailsPage(params) {
    const bookId = params.data.id;
    bookDetailsPage(app, bookId);
}

function renderAboutPage() {
    app.innerHTML = `
        <div class="container mt-5">
            <h1>About This Application</h1>
            <p>This is a sample single-page application built with vanilla JavaScript and Navigo for routing. It demonstrates how to fetch and display data from a backend API.</p>
        </div>
    `;
}

function render404() {
    app.innerHTML = `
        <div class="hero">
            <h2>404 - Page Not Found</h2>
            <p>The page you're looking for doesn't exist.</p>
            <a href="/" data-navigo class="btn">Go Home</a>
        </div>
    `;
}


// Define routes
router
    .on('/', renderHomePage)
    .on('/books', renderBooksPage)
    .on('/books/:id', (params) => renderBookDetailsPage(params))
    .on('/about', renderAboutPage)
    .notFound(render404);

// Resolve the initial route
router.resolve();