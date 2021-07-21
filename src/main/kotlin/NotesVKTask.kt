<<<<<<< HEAD
package ru.netology
=======
>>>>>>> 784537557c197ff98bf91cc27b4d22d5efa7bbd5
import java.lang.IndexOutOfBoundsException



fun main() {

    val notesVK = NotesVKTask()

    val note0 = Note("Title_ONE", "Text of note_ZERO")
    val note1 = Note("Title_TWO", "Text of note_ONE")


    println("Note was create with ID: ${notesVK.add(note0)}")
    val idZero = 0
    val idOne = 1

    println("\n Try deleting not existing note...")

    if (notesVK.del(idOne) == 1) println("$idOne deleted successfully!") else
        println("Note $idOne is not found!")

    println("Try deleting existing note")

    if (notesVK.del(idZero) == 1) println("$idZero deleted successfully!") else
        println("Note $idZero is not found!")

    notesVK.add(note0)
    println("\nTry getting text from not existing note... ")
    println("Text from a note index $idOne : ${notesVK.get(idOne)?.text}")
    println("Try getting text from existing note...")
    println("Text from a note index $idZero : ${notesVK.get(idZero)?.text}")

    println("\n Try to change not existing note...")
    if (notesVK.edit(idOne, "Title changed", "New note text") == 1)
        println("Note index $idOne changed successfully")
    else println("Note index $idOne not found!")
    println("\n Try to change existing note...")
    if (notesVK.edit(idZero, "Title changed", "New note text") == 1)
        println("Note index $idZero changed successfully")
    else println("Note index $idZero not found!")

    val noteId = notesVK.add(note0)
    println("Note created successfully id ${noteId}")

    val commentOne = Comment(noteId, "Uncensored rude comment!", 2)
    val commentTwo = Comment(noteId, "Another uncensored rude comment!", 2)
    val commentThree = Comment(noteId, "The worst comment!", 2)


    println("\nNew comment was taken an id  ${notesVK.createCom(commentOne)} ${commentOne.id}")
    println("\nNew comment was taken an id  ${notesVK.createCom(commentTwo)} ${commentTwo.id}")

    println("\nAll comments to note $noteId : ")
    println(notesVK.getComment(noteId))

    println("\n Trying to delete not existed comment...")
    if (notesVK.delRestCom(commentThree, true) == 1)
        println("Comment delete successfully!") else
        println("Comment not found")
    println("\n Trying to delete existed comment...")
    if (notesVK.delRestCom(commentOne, true) == 1)
        println("Comment delete successfully!") else
        println("Comment not found!")

    println("\n All comments to note $noteId : ")
    println(notesVK.getComment(noteId))

    println("Trying to restore not existing comment... ")
    if (notesVK.delRestCom(commentThree, false) == 1)
        println("Comment successfully restored!") else
        println("Comment not found!")
    println("Trying to restore existing comment... ")
    if (notesVK.delRestCom(commentOne, false) == 1)
        println("Comment successfully restored!") else
        println("Comment not found!")

    println("\n All comments to note $noteId : ")

    println(notesVK.getComment(noteId))
}

//ready
interface Crud<T> {
    fun add(element: T): Int
    fun del(id: Int): Int
    fun get(id: Int): T?
    fun edit(id: Int, title: String, text: String): Int
}


class Note(var title: String, var text: String) {
    var id: Int = 0
    var comments: MutableList<Comment> = mutableListOf()

    fun createCom(element: Comment): Int? {
        val NoteId = this.id
        var CommentId = element.id

        println("first comment $NoteId $CommentId")
        CommentId = this.comments.size

        this.comments.add(element)
        println("second comment ${this.comments.size} $CommentId")
        return this.comments.last().id
    }

    //ready
    fun delRestCom(id: Int?, deleleStatus: Boolean): Int {
        for ((indexComment, comment) in this.comments.withIndex()) {
            if (comment.id == id) {
                comments.get(indexComment).del = deleleStatus
                return 1
            }
        }
        return 0
    }

}

//ready
class Comment(val noteId: Int, val message: String, val replyTo: Int) {
    var id: Int? = null
    var del: Boolean = false
}

class NotesVKTask : Crud<Note> {
    var notes: MutableList<Note> = mutableListOf()

    fun findNote(noteId: Int): Note? {
        for (note in notes) {
            if (note.id == noteId) {
                return note
            }
        }
        return null

    }

    fun delRestCom(element: Comment, del: Boolean): Int {
        val note = findNote(element.noteId)
        return if (note == null) 0
        else note.delRestCom(element.id, del)
    }

    fun getComment(noteId: Int): String {
        val note = findNote(noteId)
        return if (note == null) ""
        else {
            var retValue = ""
            val comments = note.comments
            for (comment in comments) {
                if (!comment.del) retValue += (comment.message + "\n")
            }
            retValue
        }
    }

    fun createCom(element: Comment): Int? {
        val note = findNote(element.noteId)
        return if (note == null) 0 else {
            return note.createCom(element)
        }
    }

    override fun add(element: Note): Int {
        element.id = notes.size
        notes.plusAssign(element)
        return notes.last().id
    }

    override fun del(id: Int): Int {
        val retVal = try {
            notes.removeAt(id)
            1
        } catch (e: IndexOutOfBoundsException) {
            180
        }
        return retVal
    }

    override fun get(id: Int): Note? {
        val retVal = try {
            notes[id]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
        return retVal
    }

    override fun edit(id: Int, title: String, text: String): Int {
        val element = try {
            notes[id]
        } catch (e: IndexOutOfBoundsException) {
            return 0
        }
        element.title = title
        element.text = text
        return 1
    }
}
