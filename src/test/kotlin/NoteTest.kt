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

        var CommentId = 1

        val result = noteTest.createCom(commentTest)

        assertEquals(null, result)
    }

    @Test
    fun delRestComTest() {
        val noteTest = Note("Test NOTE", "Text of TEST note")
        val commentTest = Comment(99, "NICE TEST COMMENT!", 0)

        val result = noteTest.delRestCom(99, true)

        assertEquals(0, result)

    }

    @Test
    fun findNoteTest() {
        val notesVKTask = NotesVKTask()
        val noteTest = Note("Test NOTE", "Text of TEST note")
        var notes: MutableList<Note> = mutableListOf()
        val commentTest = Comment(1, "NICE TEST COMMENT!", 1)

        notes.add(noteTest)
        val noteId = commentTest.noteId

        val result = notesVKTask.findNote(noteId)

        assertEquals(null, result)
    }

    @Test
    fun delRestComNotesVKTask() {
        val notesVKTask = NotesVKTask()
        val commentTest = Comment(1, "NICE TEST COMMENT!", 1)
        val result = notesVKTask.delRestCom(commentTest, true)

        assertEquals(0, result)

    }

    @Test
    fun getCommentNotesVKTask() {
        val noteId = 1
        val notesVKTask = NotesVKTask()
        val result = notesVKTask.getComment(noteId)

        assertEquals("", result)

    }

    @Test
    fun delNotesVKTask() {
        val id = 1
        val notesVKTask = NotesVKTask()

        val result = notesVKTask.del(id)

        assertEquals(180, result)

    }

    @Test
    fun getNotesVKTask() {
        val id = 1
        val notesVKTask = NotesVKTask()
        val result = notesVKTask.get(id)

        assertEquals(null, result)

    }

    @Test
    fun editNotesVKTask() {
        val id = 1
        val title = "aaaaaaaaaa"
        val text = "ssssssssssss"

        val notesVKTask = NotesVKTask()

        val result = notesVKTask.edit(id, title, text)

        assertEquals(0, result)

    }


}

