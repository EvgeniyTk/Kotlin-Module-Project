
class NotesApp {
    private val archives = mutableListOf<Archive>()
    fun start() {
        println("Добро пожаловать в приложение \"Заметки\"")
        while (true) {
            viewMenu(-1)
            val select: Int = inputInt()
            when (select) {
                0 -> createArchive()
                in 1..archives.size -> selectArchive(select - 1)
                archives.size + 1 -> {
                    println("Работа приложения \"Заметки\" завершена")
                    break
                }
                else -> inputError()
            }
        }

    }


    private fun createArchive() {
        print("Введите название архива: ")
        val archiveName = inputString()
        val archive = Archive(archiveName, mutableListOf())
        archives.add(archive)
    }

    private fun selectArchive(archive : Int) {
        while (true) {
            viewMenu(archive)
            when (val input = inputInt()) {
                0 -> createNote(archive)
                in 1..archives[archive].notes.size -> selectNote(archive, input-1)
                archives[archive].notes.size + 1 -> return
                else -> inputError()
            }
        }
    }

    private fun selectNote(archive: Int, note: Int){
        val noteMenu = """
            -- Архив "${archives[archive].name}". Заметка "${archives[archive].notes[note].nameNote}" --
            -- Текст заметки: ${archives[archive].notes[note].textNote} --
                          
            0. Назад
                          
            Введите команду: """.trimIndent()
        println(noteMenu)
        while(true){
            when(inputInt()){
                0 -> return
                else -> {
                    inputError()
                    println(noteMenu)
                }
            }
        }
    }

    private fun createNote(element : Int) {
        val note: Note = inputNote()
        archives[element].notes.add(note)
        println("\nЗаметка \"${note.nameNote}\" создана.")
    }

    private fun inputNote(): Note {
        print("Введите имя зaметки: ")
        val name = inputString()
        print("Введите текст зaметки \"$name\": ")
        val text = inputString()
        return Note(name, text)
    }

    private fun viewMenu(element : Int) {
        var menu = ""
        if (element >= 0) {
            menu+="\n -- Архив \"${archives[element].name}\" --\n0. Создать заметку"
            for (i in 0 until archives[element].notes.size) {
                menu+="\n${i + 1}. Заметка \"${archives[element].notes[i].nameNote}\""
            }
            menu+= "\n${archives[element].notes.size + 1}. Назад"
        }

        else {
            menu+="\n-- Меню --0\n0. Создать архив заметок"
            for (i in 0 until archives.size) {
                menu+="\n${i + 1}. Архив \"${archives[i].name}\""
            }
            menu+="\n${archives.size + 1}. Выход"

        }
        print("$menu\n\nВведите команду: ")
    }

}