package by.rekuts.travelagency.domain;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HotelEnumArrayType implements UserType {

    private static final int SQL_TYPE = Types.ARRAY;

    @Override
    public Object nullSafeGet(ResultSet rs, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        var dbArray = rs.getArray(strings[0]);
        var array = (Object[]) dbArray.getArray();
        var features = new ArrayList<>();
        for(Object ob : array) {
            for(var f : Hotel.Features.values()) {
                if (f.getValue().equals(ob.toString())) {
                    features.add(ob.toString());
                }
            }
        }
        return features;
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, Object object, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        var connection = statement.getConnection();
        Array array;
        if (object != null) {
            var list = (List<String>) object;
            var features = list.toArray();
            array = connection.createArrayOf("features", features);
        } else {
            array = connection.createArrayOf("features", new Object[0]);
        }
        statement.setArray(i, array);
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) {
        return cached;
    }

    @Override
    public Object deepCopy(final Object o) {
        return o == null ? null : ((ArrayList<Hotel.Features>)o).clone();
    }

    @Override
    public Serializable disassemble(final Object o) {
        return (Serializable) o;
    }

    @Override
    public boolean equals(final Object x, final Object y) {
        return x == null ? y == null : x.equals(y);
    }

    @Override
    public int hashCode(final Object o) {
        return o == null ? 0 : o.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) {
        return original;
    }

    @Override
    public Class<List<Hotel.Features>> returnedClass() {
        return (Class<List<Hotel.Features>>) Collections.<Hotel.Features>emptyList().getClass();
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{SQL_TYPE};
    }
}