package by.rekuts.travelagency.dao.subjects;

import by.rekuts.travelagency.dao.subjects.Hotel.Features;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnumArrayType implements UserType {

    private static final int SQL_TYPE = Types.ARRAY;


    @Override
    public Object nullSafeGet(ResultSet rs, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        Array dbArray = rs.getArray(strings[0]);
        Object[] array = (Object[]) dbArray.getArray();
        List<Features> features = new ArrayList<>();
        Arrays.stream(array).forEach(feature -> features.add(Features.valueOf(feature + "")));
        return features;
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, Object object, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        Connection connection = statement.getConnection();

        List<Features> castObject = (List<Features>) object;
        Object[] features = castObject.stream().map(Enum::name).toArray();
        Array array = connection.createArrayOf("feature", features);

        statement.setArray(i, array);
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) {
        return cached;
    }

    @Override
    public Object deepCopy(final Object o) {
        return o == null ? null : ((ArrayList<Features>)o).clone();
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
    public Class<List<Features>> returnedClass() {
        return (Class<List<Features>>) Collections.<Features>emptyList().getClass();
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{SQL_TYPE};
    }
}