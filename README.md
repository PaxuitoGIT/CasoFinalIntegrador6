# Caso Final Integrador 6

Link del reposistorio: https://github.com/PaxuitoGIT/CasoFinalIntegrador6

Para ejecutar el programa, ejecute el Main.java

## Explicación de los módulos

### Gestión de Datos Dinámicos

Para este módulo, he creado las clases Pareja y ListaDatos para hacer uso de la clase genérica e implementarlo en la interfaz gráfica de la tabla. En un primer momento, pensé en implementar que la tabla pudiese leer un jar que contenía una pequeña base de datos de un MySQL pero me fue bastante complicado crear y agregarlo al proyecto. Al final se ha quedado en una tabla en la que puedes agregar una ID, un nombre y las ventas que haya tenido, modificar las casillas seleccionando la fila, eliminar la fila y si se tiene una lista muy grande, poder buscar el nombre y que devuelva la ID asociada (este no usa el hashmap). Además, cuenta con una ordenación pinchando sobre el encabezado de la columna.

### Análisis y Organización de Información

Para la primera parte, hice una tabla en la que se pudiera ver nombres como ejemplo y que estén ordenados mediante un TreeSet y también el poder agregar nombres que se ordenaran todo en vivo y una tabla de productos y ventas que se ordenaran mediante el Collections.sort y poder agregar valores en vivo.

Para la segunda parte, hice otra tabla que se pudiese filtrar por clientes. El fallo que tiene es que puede filtrar con los clientes del ejemplo iniciado pero no puede para los valores nuevos que le hayas entregado.

### Mapas y Asociación de Datos

En esta parte, creé una tabla en la que permite introducir al usuario el número con la letra que se quiera asociar y poder buscar la relación que tenga dicho número para que devuelva la letra asociada (este sí usa el hashmap).

### Indexación y Visualización de Archivos

Por último, creé una tabla que permite ver los nombres y la ruta de los archivos de dicho directorio seleccionado y que estén ordenados alfabéticamente. Por defecto, está dirigida a la ruta del proyecto siempre.

### Extras

Para una mayor comodidad, he creado en el Main un menú principal que pueda acceder al resto de las aplicaciones con un solo click al botón y al que se desee.
