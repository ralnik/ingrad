package ru.ralnik.ingrad.sqlitedb;

import android.app.Activity;

import java.util.Date;

import lombok.Getter;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.exception.IngradException;

public class DbManager {
    @Getter
    private String query;

    public DbManager() {
        query = "select * from flats where 1=1 ";
    }

    public void setQuery(String query) {
        if (!query.contains("where") && !query.contains("where".toUpperCase())) {
            new IngradException(IngradContex.getMainActivity(), "Запрос не содержит ключевое слово 'WHERE'");
        }
        this.query = query + " ";
    }

    /**
     * Задается параметр sql запроса по количеству комнат
     * @int countRoom количество комнат
     */
    public DbManager setRooms(int countRoom) {
        query += "and rooms=" + countRoom;
        return this;
    }

    /**
     * задается параметр по площади в диапозоне с шагом дельта
     * @int площадь квартиры
     * @int дельта
     */
    public DbManager setSquare(int square, int delta) {
        query += " and (quantity>=%s and quantity<%s)";
        query = String.format(query, square, square+delta);
        return this;
    }

    /**
     * задается параметр по площадям в диапозоне с шагом дельта
     * @int список площадей квартиры
     * @int дельта
     */
    public DbManager setSquares(int[] squares, int delta) {
        for (int square : squares) {
            query += " and (quantity>=%s and quantity<%s)";
            query = String.format(query, square, square+delta);
        }
        return this;
    }

    public DbManager whereAnd(String column, Object value) {
        if (value instanceof String) {
            query += " and %s = '%s'";
        } else {
            query += " and %s = %s";
        }
        query = String.format(query, column, value);
        return this;
    }

    public DbManager whereAndIn(String column, Object[] values) {
        if (values.length == 0) {
            return this;
        }
        String resultIn = null;
        if (values[0] instanceof String) {
            for (Object val : values) {
                if (resultIn.isEmpty())
                    resultIn = (String) "'" + val + "'";
                else resultIn += resultIn + "," +  "'" + val + "'";
            }
        } else {
            for (Object val : values) {
                if (resultIn.isEmpty())
                    resultIn = (String)  val ;
                else resultIn += resultIn + "," + val;
            }
        }
        query += " and " + column + " in (" + resultIn + ") ";

        return this;
    }

    /**
        * Добавляется параметр ввиде sql
        * напр.:  ' and room = 1 '
        * или     ' or room > 3 '
        * ВНИМАНИЕ: обязательно должен начинаться с AND или OR
        *
     */
    public DbManager where(String param) {
         query += param;
         return this;
    }

    /**
     * Заменяет парамерт указаный в запросе на его значение
     * */
    public DbManager whereParams(String[] nameParam, Object[] valueParams) {
        for (int i=0; i<nameParam.length;i++) {
            query = query.replaceAll(nameParam[i], (String) valueParams[i] + "\n");
        }
        return this;
    }


    /**
     * Список параметоров который будет перечисляться через and
     * @param nameParam
     * @param valueParams
     * @return
     */
    public DbManager whereParamsAnd(String[] nameParam, Object[] valueParams) {
        for (int i=0; i<nameParam.length;i++) {
            if (valueParams[i] instanceof String) {
                query += " and " + nameParam[i] + "='" + valueParams[i] + "'";
            } else if (valueParams[i] instanceof Date) {
                query += " and " + nameParam[i] + "='" + valueParams[i] + "'";
            } else {
                query += " and " + nameParam[i] + "=" + valueParams[i];
            }
        }
        return this;
    }

    /**
     * Список параметоров который будет перечисляться через OR
     * @param nameParam
     * @param valueParams
     * @return
     */
    public DbManager whereParamsOr(String[] nameParam, Object[] valueParams) {
        String result = "";
        for (int i=0; i<nameParam.length;i++) {
            if (valueParams[i] instanceof String) {
                result += " OR " + nameParam[i] + "='" + valueParams[i] + "'";
            } else if (valueParams[i] instanceof Date) {
                result += " OR " + nameParam[i] + "='" + valueParams[i] + "'";
            } else {
                result += " OR " + nameParam[i] + "=" + valueParams[i];
            }
        }
        query += " and ( 1=1 " + result + ")";
        return this;
    }
}
