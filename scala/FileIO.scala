import java.io._

object FileIO extends App {
  val filePath = "/tmp/someFile.txt"
  val file = new File(filePath)
  
  //Check file exists
  println(s"file: ${filePath}, exists: ${file.exists} \n")
  
  //Create file
  println(s"Create file: ${filePath}")
  val bw = new BufferedWriter(new FileWriter(file))
  bw.write("test")
  bw.flush()
  bw.close()
  println(s"file: ${filePath}, exists: ${file.exists} \n")
  
  
  //Delete file
  println(s"Delete file: ${filePath}")
  val isDeleted = file.delete()
  println(s"file deleted: ${isDeleted}")
  println(s"file: ${filePath}, exists: ${file.exists}")
}
