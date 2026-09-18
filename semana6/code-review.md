src/test/java/com/taskflow/unit/BusquedaTareasServiceTest.java

La especificación exige que `null` y una cadena en blanco no lleguen al repositorio, pero estas aserciones solo comprueban la excepción. Si el servicio llamara al repositorio antes de fallar, el test seguiría pasando; añade una verificación de cero interacciones para cubrir ese contrato.

src/test/java/com/taskflow/slice/BusquedaTareasControllerTest.java

Este comentario mezcla una duda sobre DTOs con una afirmación en inglés que contradice la firma real: buscarPorTitulo devuelve List<Task> y el controller hace el mapeo. La anotación puede confundir sobre el contrato probado; déjala como una descripción factual en español o elimínala.


