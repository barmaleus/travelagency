package by.rekuts.travelagency.view.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot delete entity. There are some dependent objects.")
class DeleteException private(ex: RuntimeException) extends RuntimeException(ex) {
  def this(message: String) = this(new RuntimeException(message))
  def this(message: String, cause: Throwable) = this(new RuntimeException(message, cause))
  def this(cause: Throwable) = this(new RuntimeException(cause))
}

object DeleteException {
  def apply(message: String) = new DeleteException(message)
  def apply(message: String, cause: Throwable) = new DeleteException(message, cause)
  def apply(cause: Throwable) = new DeleteException(cause)
}
