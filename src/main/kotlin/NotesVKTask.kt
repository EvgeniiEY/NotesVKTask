//fun main(){
//    val notesService = NotesService()
//
//    val note0 = Note("Заголовок 0","Текст заметки 0")
//    val note1 = Note("Заголовок 1","Текст заметки 1")
//
//    println("Успешно создана заметка с идентификатором ${notesService.add(note0)}")
//    val idx0 = 0
//    val idx1 = 1
//    println("\nПопытка удаления несуществующей заметки..")
//    if (notesService.del(idx1) == 1) println("Успешно удалили заметку $idx1") else println("Заметка $idx1 не найдена")
//    println("Попытка удаления существующей заметки..")
//    if (notesService.del(idx0) == 1) println("Успешно удалили заметку $idx0") else println("Заметка $idx0 не найдена")
//
//    notesService.add(note0)
//    println("\nПопытка получения текста из несуществующей заметки..")
//    println("Текст заметки с индексом $idx1 : ${notesService.get(idx1)?.text}")
//    println("Попытка получения текста из существующей заметки..")
//    println("Текст заметки с индексом $idx0 : ${notesService.get(idx0)?.text}")
//
//    println("\nПопытка изменения несуществующей заметки..")
//    if (notesService.edit(idx1,"Заголовок изменен","Новый текст заметки") == 1) println("Заметка с индексом $idx1 успешно изменена")
//    else println("Заметка с индексом $idx1 не найдена")
//    println("Попытка изменения существующей заметки..")
//    if (notesService.edit(idx0,"Заголовок изменен","Новый текст заметки") == 1) println("Заметка с индексом $idx0 успешно изменена")
//    else println("Заметка с индексом $idx0 не найдена")
//
//    val com0 = Comment(note0.id,"Жуткий коммент на заметку..",2)
//    val com1 = Comment(note0.id,"Еще более жуткий оммент на заметку..",2)
//    val com2 = Comment(note0.id,"Еще более жуткий оммент на заметку..",2)
//    println("Успешно создана заметка с идентификатором ${notesService.add(note1)}")
//    // val comId = note0?.createCom(com0)
//    // println("\nНовый коммент получил идентификар ${note0.createCom(Comment(note0.id,"Жуткий коммент на заметку..",2))}")
//    println("\nНовый коммент получил идентификар ${notesService.createCom(com0)}   ${com0.id}")
//    println("\nНовый коммент получил идентификар ${notesService.createCom(com1)}   ${com1.id}")
//
//    println("\nВсе комментарии заметки $idx0 : ")
//    println(notesService.getCom(idx0))
//
//    println("\nПопытка удаления несуществующего коментария..")
//    if (notesService.delRestCom(com2,true) == 1)println("Коментарий успешно удален") else println("Коментарий не найден")
//    println("Попытка удалить существующий коментарий..")
//    if (notesService.delRestCom(com0,true) == 1)println("Коментарий успешно удален") else println("Коментарий не найден")
//
//    println("\nВсе комментарии заметки $idx0 : ")
//    println(notesService.getCom(idx0))
//
//    println("\nПопытка восстановить несуществующий коментарий..")
//    if (notesService.delRestCom(com2,false) == 1)println("Коментарий успешно восстановлен") else println("Коментарий не найден")
//    println("\nПопытка восстановить существующий коментарий..")
//    if (notesService.delRestCom(com0,false) == 1)println("Коментарий успешно восстановлен") else println("Коментарий не найден")
//
//    println("\nВсе комментарии заметки $idx0 : ")
//    println(notesService.getCom(idx0))
//}
//
//interface CrudService<T>{
//    fun add(element: T):Int
//    fun del(id: Int):Int
//    fun get(id:Int):T?
//    fun edit(id:Int,titl:String,text:String):Int
//}
//
//class Note (var title: String,var text: String){
//    var id:Int = 0
//    var comments:MutableList<Comment> = mutableListOf()
//
//    fun createCom(element: Comment): Int? {
//        println("001  ${this.id}  ${element.id}")
//        element.id = this.comments.size
//        this.comments.add(element)
//        println("002  ${this.comments.size}  ${element.id}")
//        return this.comments.last().id
//    }
//    // логика одинаковая для удаления\восстановления
//    fun delRestCom(id: Int?,del:Boolean):Int {
//        for ((indexCm, comment) in this.comments.withIndex()){//проход по комментам
//            if (comment.id == id) {
//                //comments.removeAt(indexCm)
//                comments.get(indexCm).del = del
//                return 1
//            }
//        }
//        return 0
//    }
//}
//
//class Comment (val noteId: Int,val message:String,val replyTo:Int){
//    var id:Int? = null
//    var del:Boolean = false
//}
//
//class NotesService: CrudService<Note>{
//    var notes: MutableList<Note> = mutableListOf()
//
//    fun findNote(noteId: Int):Note?{ //for (note in notes)
//        for (note in notes){
//            if (note.id == noteId) {
//                return note
//            }
//        }
//        return null
//    }
//    // логика одинаковая для удаления\восстановления
//    // del = true  удаление false - восстановление
//    fun delRestCom(element: Comment,del:Boolean): Int {
//        val note = findNote(element.noteId)
//        return if (note == null) 0 else note.delRestCom(element.id,del)
//    }
//
//    fun getCom(noteId: Int):String {
//        val note = findNote(noteId)
//        return if (note == null) "" else {
//            var retValue = ""
//            val comments =note.comments
//            for (comment in comments){//проход по комментам
//                if (! comment.del) retValue += (comment.message + "\n")
//            }
//            retValue
//        }
//    }
//
//    fun createCom(element: Comment):Int? {
//        val note = findNote(element.noteId)
//        return if (note == null) null else {
//            return note.createCom(element)
//        }
//    }
//
//    override fun add(element: Note):Int {
//        element.id = notes.size
//        notes.plusAssign(element)
//        return notes.last().id
//    }
//
//    override fun del(id: Int):Int {
//        val retVal = try {
//            notes.removeAt(id)
//            1
//        } catch (e: IndexOutOfBoundsException) {
//            180
//        }
//        return retVal
//    }
//
//    override fun get(id: Int): Note? {
//        val retVal = try {
//            notes[id]
//        } catch (e: IndexOutOfBoundsException) {
//            null
//        }
//        return retVal
//    }
//
//    override fun edit(id: Int,titl: String,text: String):Int {
//        val element = try {
//            notes[id]
//        } catch (e: IndexOutOfBoundsException) {
//            return 0
//        }
//        element.title = titl
//        element.text = text
//        return 1
//    }
//}


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
