# Guía de GitHub y presentación

## Estructura del repositorio
biblioteca/ → src/ (Main.java, Libro.java, Biblioteca.java, Almacenamiento.java), data/, docs/, README.md, .gitignore

## Flujo de ramas (equipo de 3 como ejemplo)
- `main`: solo código estable (proteger la rama, exigir 1 revisión).
- `develop`: integración.
- Ramas por tarea: `feature/almacenamiento`, `feature/libros`, `feature/prestamos`, `feature/menu`, `docs/manual`, `docs/diagrama`, `fix/validaciones`, `test/pruebas`.

## 3 Pull Requests mínimos por integrante (ejemplo)
| Integrante | PR 1 | PR 2 | PR 3 |
|---|---|---|---|
| A | feature/almacenamiento | feature/menu | docs/diagrama |
| B | feature/libros | fix/validaciones | docs/manual |
| C | feature/prestamos | test/pruebas | docs/presentacion |

## Comandos
git checkout -b feature/libros
git add . && git commit -m "feat: busca libros por título o autor"
git push -u origin feature/libros   # luego abrir el PR en GitHub
Conflictos: `git checkout develop && git pull && git checkout feature/x && git merge develop`, resolver, commit y push.
Tip: dos personas editan `Main.java` (menú) para provocar y practicar un conflicto real.

## Presentación (10 min, ~8 diapositivas)
1. Portada y equipo (0:30) 2. Objetivo y funciones (1:00) 3. Diagrama de flujo (1:30) 4. Estructura modular (1:30) 5. Demo en vivo (3:00) 6. Trabajo en GitHub: ramas, PRs, conflicto resuelto (1:30) 7. Retos y aprendizajes (0:45) 8. Mejoras futuras (0:15)
