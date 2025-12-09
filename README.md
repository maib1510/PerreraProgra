# 🐾 PerreraProgra — Gestión para un refugio de animales

PerreraProgra es una aplicación de escritorio Java (Swing) para gestionar un refugio/perrera: visualizar animales para adopción, registrar adopciones, gestionar una mini‑tienda y perfiles de usuario. Este README explica la estructura, uso rápido, flujo de la interfaz y secciones útiles para desarrolladores y colaboradores. ¡Incluye emoticonos para hacerlo más dinámico! 🎉

---

## 📌 Tabla de contenidos
- [Resumen rápido](#-resumen-rápido)
- [Características principales](#-características-principales)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Arranque rápido / Quick Start 🚀](#-arranque-rápido--quick-start-)
- [Recorrido por la interfaz (GUI) 🖥️](#-recorrido-por-la-interfaz-gui-)
- [Resumen de clases y funciones (rápido) 📚](#-resumen-de-clases-y-funciones-rápido-)
- [Base de datos y persistencia 💾](#-base-de-datos-y-persistencia-)
- [Problemas detectados & recomendaciones ⚠️](#-problemas-detectados--recomendaciones-)
- [Roadmap / Siguientes pasos 🛠️](#-roadmap--siguientes-pasos-)
- [Contribuir 🤝](#-contribuir-)
- [FAQ ❓](#-faq-)
- [Contacto ✉️](#-contacto-)

---

## 🧾 Resumen rápido
- Lenguaje: Java (proyecto preparado para Eclipse).
- UI: Swing (paquete `src/gui`).
- BD: SQLite (driver `org.sqlite.JDBC`, fichero en `resources/db/database.db`).
- Recursos: imágenes en `imagenes/`, noticias en `news/`, certificados en `certificados/`.

---

## ✨ Características principales
- 🐶🐱🐦🐭 Mostrar animales por tipo (perros, gatos, pájaros, roedores).
- 📝 Fichas detalladas de animales con foto circular y datos.
- ❤️ Registro de adopciones (persistente en BD).
- 🛒 Tienda con productos, compra simulada y ticket.
- 🔒 Inicio de sesión con perfil (nota: contraseñas en texto plano actualmente).
- 🖼️ Gestión visual de perfiles (cambiar imagen / color del borde).
- 📰 Carrusel de noticias con pausa/reanudar.

---

## 📁 Estructura del proyecto (lo esencial)
- src/Domain — modelos: Animal, Perro, Gato, Pajaro, Roedor, Usuario, Perfil, Producto, Adopcion.
- src/DB — GestorBD: creación/lectura/escritura de la BD.
- src/gui — ventanas, diálogos y utilidades (VentanaPrincipal, VentanaPerros, VentanaTienda, etc.).
- resources/db — fichero SQLite.
- imagenes/, news/, certificados/ — recursos estáticos usados por la UI.

---

## 🏁 Arranque rápido / Quick Start
Requisitos: JDK 8+ y el JAR de sqlite-jdbc en `lib/`.

Ejemplo (Linux/macOS):
```bash
# compilar
javac -d out -cp "lib/*" $(find src -name "*.java")

# ejecutar (ajusta el paquete de Main si hace falta)
java -cp "out:lib/*" main.Main
```

En Eclipse: Importar -> Existing Projects -> abrir `src`, añadir JARs de `lib/` al Build Path y ejecutar `Main`.

Consejos:
- Si la BD no existe, ejecutar `GestorBD.crearBBDD()` al iniciar para crear tablas.
- Asegúrate de que las carpetas `imagenes/`, `news/` y `certificados/` existen con los ficheros esperados.

---

## 🖼️ Recorrido por la interfaz (GUI)
- Pantalla de inicio (splash): `HiloBienvenida` muestra imagen a pantalla completa.
- Ventana de inicio de sesión: `VentanaInicioSesion` → valida usuario con `GestorBD.obtenerUsuarioConPerfil(...)`.
- Ventana principal: `VentanaPrincipal` → paneles para cada tipo de animal y menú (Perfil, Tienda, Noticias).
- Listados por tipo:
  - `VentanaPerros`, `VentanaGatos`, `VentanaPajaros`, `VentanaRoedores`: grids con animales, botón "Ver más".
  - Al pulsar "Ver más" → `VentanaCaracteristicas` (ficha detallada).
- Confirmación de adopción: `VentanaConfirmacion` → genera certificado (.txt) y registra adopción en BD.
- Tienda: `VentanaTienda` → listado con `TiendaModel`, compra simulada, actualización en BD y ticket.
- Perfil y mascotas: `VentanaPerfil`, `VentanaMascotas`.

---

## 📚 Resumen de clases y funciones (rápido)
- Domain:
  - Animal (abstract): getters/setters, sonidoAnimales(), getImagenPath().
  - Perro/Gato/Pajaro/Roedor: implementan sonido e imagen.
  - Usuario / Perfil: datos del usuario; Perfil almacena username/password (texto plano ahora).
  - Producto: id, nombre, categoría, precio, unidades, agotado.
  - Adopcion: animal, usuario, fecha_adopcion.
- DB.GestorBD:
  - crearBBDD(), borrarBBDD()
  - obtenerUsuarioConPerfil(username,password)
  - insertarUsuario(Usuario), insertarAnimal(Animal), insertarProducto(Producto)
  - insertarMascota(Adopcion) — registra adopción con fecha actual
  - obtenerMascotasUsuario(Usuario) — devuelve lista de Animal
  - cargarProductos(), actualizarProducto(Producto)
  - estaAdoptadoPorId(int id), actualizarAnimal(int id)
- GUI:
  - HiloBienvenida, HiloCargar — animaciones y diálogos.
  - NewsTicker — carrusel de noticias.
  - TiendaModel — model para JTable.
  - Ventana* — ventanas principales y utilitarias.

---

## 💾 Base de datos y persistencia
- Driver: org.sqlite.JDBC (asegúrate de tener `sqlite-jdbc` en `lib/`).
- Fichero: `resources/db/database.db` (constante en GestorBD).
- Tablas: perfil, usuario, animal, adopcion, producto.
- Notas importantes:
  - Campos adoptado/agotado almacenados como INTEGER (0/1).
  - Inserciones usan `INSERT OR IGNORE` — evita duplicados pero puede ocultar fallos.

---

## ⚠️ Problemas detectados & recomendaciones
- Seguridad: contraseñas en texto plano. Reemplazar por hashing (bcrypt/Argon2). 🔒
- Identificación por índices: la UI usa índices + constantes (p.ej. i+13, i+25) para mapear a id_animal. Mejor usar id reales desde BD. 🧩
- VentanaRegistro no persiste el usuario (no llama a `insertarUsuario`). ✔️ Recomendar implementar persistencia en registro.
- Rutas a recursos relativas: usar getResourceAsStream al empaquetar en JAR o verificar existencia antes de leer.
- Buscar por nombre en `insertarMascota` puede fallar si hay nombres duplicados. Mejor usar id_animal.

---

## 🛠️ Roadmap (sugerido)
- [ ] Implementar hashing de contraseñas (bcrypt).
- [ ] Cambiar flujo para usar id_animal real (no offsets).
- [ ] Validaciones más robustas en formularios.
- [ ] Tests unitarios para GestorBD y lógica crítica.
- [ ] Internacionalización (i18n) y textos externalizados.
- [ ] Empaquetar como JAR ejecutable con recursos embebidos.


