package by.rekuts.travelagency.domain

import java.io.Serializable
import java.sql.{Array, PreparedStatement, ResultSet, SQLException, Types}
import java.util
import java.util.Collections

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.UserType

import scala.collection.mutable.ListBuffer

class HotelEnumArrayType extends UserType {
  @throws[SQLException]
  override def nullSafeGet(rs: ResultSet, strings: scala.Array[String], sharedSessionContractImplementor: SharedSessionContractImplementor, o: AnyRef) = {
    val dbArray = rs.getArray(strings(0): String)
    val array = dbArray.getArray.asInstanceOf[scala.Array[Any]]
    var features = new ListBuffer[String]
    for (ob <- array) {
      Features.values.foreach(feature => {
        def foo(feature: Enumeration#Value) = {
          if (feature.toString == ob.toString) features += (ob.toString)
        }
        foo(feature)
      })
    }
    features.toList
  }

  @throws[SQLException]
  override def nullSafeSet(statement: PreparedStatement, any: Any, i: Int, sharedSessionContractImplementor: SharedSessionContractImplementor): Unit = {
    val connection = statement.getConnection
    var array: Array = null
    if (any != null) {
      val list = any.asInstanceOf[List[AnyRef]]
      val features = list.toArray
      array = connection.createArrayOf("features", features)
    }
    else array = connection.createArrayOf("features", new scala.Array[AnyRef](0))
    statement.setArray(i, array)
  }

  override def assemble(cached: Serializable, owner: Any) = cached.asInstanceOf[Object]

  override def deepCopy(o: AnyRef): AnyRef = {
    if (o == null) null
    else {
      var resultList = new ListBuffer[String]
      o.asInstanceOf[List[String]].foreach(feature => resultList += feature)
      resultList.toList
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

  override def returnedClass: Class[util.List[Features.type]] = Collections.emptyList[Features.type].getClass.asInstanceOf[Class[util.List[Features.type]]]

  override def sqlTypes: scala.Array[Int] = scala.Array[Int](HotelEnumArrayType.SQL_TYPE)

  object HotelEnumArrayType {
    val SQL_TYPE: Int = Types.ARRAY
  }
}


