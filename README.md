# tarea4_web

tarea4_web

Para que la aplicación funcione en su totalidad, se deben iniciar ambos servidores (Flask y Spring)

1. Abrir una terminal en tarea3 y ejecutar:
   > python -m venv venv
2. Instalar las dependencias con:
   > .\venv\Scripts\activate
3. Una vez creado el ambiente y estando en éste, cargar y poblar la base de datos con:
   > python .\setup_db.py
4. Correr el servidor:
   > python app.py
5. Abrir otra consola en /tarea4 y ejecutar:
   > ./mvnw spring-boot:run
6.

## 6.1 Para dirigirse al inicio de la app:

> http://localhost:5000

## 6.2 Para dirigirse directamente a las evaluaciones:

> http://localhost:8080/evaluaciones

- Se implementó la vista "evaluaciones"
- Se modificó la navbar para que incluyera un enlace a "Evaluar actividades"
- Se agregó una tabla con las actividades con fecha de termino previa a la actual
- En la última columna de la tabla nueva, se agregó un botón "Evaluar". Al clickearse se permite enviar una nueva calificación entre 1 y 7, de no cumplirse, se arroja una alerta

**_NOTAS_**

- Si bien tabla-nota.sql es parte de la tarea4, se optó por dejarla en tarea3/database/ para que el script setup_db.py cargue todas las tablas de la app de manera sencilla
