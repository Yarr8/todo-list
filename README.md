# TODO List CLI Application

A simple console-based TODO list manager that lets you create, view, edit, and organize tasks using text commands.

## Features
- Add new tasks  
- List all tasks  
- Edit existing tasks  
- Delete tasks  
- Filter tasks by status  
- Sort tasks by status or due date  
- Exit the application  

## Commands

### `add`
Add a new task.  
**Syntax:**
`add name=Name desc=Description date=2025-12-31`
- `name` – task title (no spaces)  
- `desc` – short description (no spaces)  
- `date` – due date in `yyyy-MM-dd` format  

---

### `list`
Show all existing tasks.  
**Syntax:**
`list`

---

### `edit`
Edit an existing task.  
**Syntax:**
`edit id=UUID name=NewName desc=NewDescription date=2026-12-31 status=DONE`

- `id` – ID of the existing task  
- Other parameters are optional  
- `status` can be one of: `TODO`, `IN_PROGRESS`, `DONE`  

---

### `delete`
Delete a task by ID.  
**Syntax:**
`delete id=UUID`

---

### `filter`
Show only tasks with a specific status.  
**Syntax:**
`filter status=TODO`

- `status` can be: `TODO`, `IN_PROGRESS`, `DONE`

---

### `sort`
Sort tasks by a given field.  
**Syntax:**
`sort field=status`

- `field` can be: `status` or `dueDate`

---

### `exit`
Exit the application.  
**Syntax:**
`exit`

---

## Example usage
```
add name=Train desc=Gym date=2025-12-31
list
filter status=TODO
edit id=550e8400-e29b-41d4-a716-446655440000 status=DONE
sort field=dueDate
exit
```
