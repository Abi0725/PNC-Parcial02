# PNC-Parcial02

*Marisol Abigail Miranda Flores 00086320*
*Diego Fabrizio Calderon Quevedo 00034022*

*Se utilizó insomnia para la prueba de endpoints*


*ENDPOINTS*

*Para crear libro: 
http://localhost:8190/create   
body:{
	"title": "El Señor de los Anillos",
    "author": "J.R.R. Tolkien",
    "isbn": "978-84-450-7179-3",
    "publicationYear": 1954,
    "language": "Español",
    "pages": 1216,
    "genre": "Fantasía"
}*

*Buscar libro por autor: http://localhost:8190/books?author=J.R.R.%20Tolkien*
*Mostrar todos los libros: http://localhost:8190/books/all*
*Buscar libro por rango de pagina: http://localhost:8190/books/range?min=300&max=2000*
*Buscar libro por genero: http://localhost:8190/books/genre?genre=Fantas%C3%ADa*