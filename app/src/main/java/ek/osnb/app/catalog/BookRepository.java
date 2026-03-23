package ek.osnb.app.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookRepository extends JpaRepository<Book,Long> {
}
