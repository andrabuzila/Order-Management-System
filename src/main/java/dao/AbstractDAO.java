package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER= Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     *
     */
    public AbstractDAO(){
        this.type=(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    /**
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * @return
     */
    public List<T> viewAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM "+type.getSimpleName();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            return createObjects(result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            ConnectionFactory.close(result);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * @param field
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * @return
     */
    private String createInsertQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        int i=0;
        for(Field fields: type.getDeclaredFields()){
            if(i<type.getDeclaredFields().length-1){
                sb.append(fields.getName() + ", "); }
            else
                sb.append(fields.getName());
            i++;
        }
        sb.append(") \n VALUES (");
        int j=0;
        while(j<i){
            if(j<i-1)
                sb.append("? , ");
            else
                sb.append("? )");
            j++;
        }
        return sb.toString();
    }

    /**
     * @param t
     */
    public void insert(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        ResultSet resultSet = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for(Field fields: type.getDeclaredFields()){
                fields.setAccessible(true);
                if(fields.get(t) instanceof Integer){
                    statement.setInt(i, (Integer) fields.get(t));
                }
                else if(fields.get(t) instanceof  String){
                    statement.setString(i, (String) fields.get(t));
                }
                i++;
            }
            statement.execute();
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @param field
     * @return
     */
    private String deleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM "+type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = deleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @param field
     * @param campuri
     * @return
     */
    private String updateQuery(String field, List<String> campuri){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE "+ type.getSimpleName());
        sb.append("\n SET ");
        int i;
        for( i= 0; i<campuri.size();i++){
            if(i<campuri.size()-1)
                sb.append(campuri.get(i) + " =?, ");
            else
                sb.append(campuri.get(i) +" =?");
        }

        sb.append("\n WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * @param t
     * @param id
     * @param campuri
     */
    public void update(T t,int id, List<String> campuri) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = updateQuery("id", campuri);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for(Field fields: type.getDeclaredFields()){
                for(int j = 0;j<campuri.size();j++)
                    if(fields.getName().compareTo(campuri.get(j))==0){
                        fields.setAccessible(true);
                        if(fields.get(t) instanceof Integer){
                            statement.setInt(i, (Integer) fields.get(t));
                        }
                        else if(fields.get(t) instanceof  String){
                            statement.setString(i, (String) fields.get(t));
                        }
                        i++;
                    }
                statement.setInt(i,id);
            }
            statement.execute();

        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


}
