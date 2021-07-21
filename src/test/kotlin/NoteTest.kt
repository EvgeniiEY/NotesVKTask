import org.junit.Test

import org.junit.Assert.*
import ru.netology.*
import ru.netology.Note


class NoteTest {


    @Test
    fun createComTEST() {
        val noteTest = Note("Test NOTE", "Text of TEST note")
        val commentTest = Comment(99, "NICE TEST COMMENT!", 1)
        val notesMassiveTest = NotesVKTask()
        val NoteId = 99
        var CommentId = 1
// Как сослаться на ф-ю в классе Note?

        val result = Note(createCom)





        assertTrue(true)
    }

    @Test
    fun findNoteTest() {
        val noteId = 1

        val result = findNote(noteId)


    }

}

