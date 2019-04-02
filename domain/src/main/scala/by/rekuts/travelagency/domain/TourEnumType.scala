package by.rekuts.travelagency.domain

import java.io.Serializable
import java.sql.{PreparedStatement, ResultSet, SQLException, Types}
import java.util

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.UserType

class TourEnumType extends UserType {
  @throws[SQLException]
  override def nullSafeGet(rs: ResultSet, strings: scala.Array[String], sharedSessionContractImplementor: SharedSessionContractImplementor, o: AnyRef) = {
    val dbTourType = rs.getArray(strings(0): String)
    var tourType: Enumeration#Value = null
    TourType.values.foreach(tType => {
      def foo(tType: Enumeration#Value) = {
        if (tType.toString == dbTourType.toString) tourType = tType
      }
      foo(tType)
    })
    tourType
  }

  @throws[SQLException]
  override def nullSafeSet(preparedStatement: PreparedStatement, value: Any, index: Int, session: SharedSessionContractImplementor): Unit = {
    if (value == null) {
      preparedStatement.setNull(index, Types.OTHER)
    } else {
      preparedStatement.setObject(index, value.toString, Types.OTHER)
    }
  }

  override def assemble(cached: Serializable, owner: Any) = cached.asInstanceOf[Object]

  override def deepCopy(o: AnyRef): AnyRef = {
    if (o == null) null
    else {
      val resultTourType = o.asInstanceOf[Enumeration#Value]
      resultTourType
    }
  }

  override def disassemble(o: Any): Serializable = o.asInstanceOf[Serializable]

  override def equals(x: Any, y: Any): Boolean = {
    if (x == null) y == null
    else x == y
  }

  override def hashCode(o: Any): Int = {
    if (o == null) 0
    else o.hashCode
  }

  override def isMutable = false

  override def replace(original: Object, target: Any, owner: Any) = original

  override def returnedClass: Class[util.List[TourType.type]] = null

  override def sqlTypes: scala.Array[Int] = scala.Array[Int](TourEnumType.SQL_TYPE)

  object TourEnumType {
    val SQL_TYPE: Int = Types.OTHER
  }
}
