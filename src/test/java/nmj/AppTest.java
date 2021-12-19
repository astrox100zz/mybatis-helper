package nmj;

import org.junit.Test;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void sqlPrinterTest()
    {
        String sql = "select " +
                "* from " +
                "table   where " +
                "id    = ? and date = ?;";
        MybatisSqlPrinter.SqlPrinter sqlPrinter = new MybatisSqlPrinter.SqlPrinter(sql);
        sqlPrinter.setNextValue("id", "1");
        sqlPrinter.setNextValue("date", new Date());
        String sql1 = sqlPrinter.getSql();
        System.out.println(sql1);
    }

    @Test
    public void sqlPrinterTest2()
    {
        String sql = "select * " +
                "from     table " +
                "where " +
                "id = 1;";
        MybatisSqlPrinter.SqlPrinter sqlPrinter = new MybatisSqlPrinter.SqlPrinter(sql);
        String sql1 = sqlPrinter.getSql();
        System.out.println(sql1);
    }

    @Test
    public void beautifyTest()
    {
        String sql = "select \n" +
                "* \n" +
                "from\n" +
                "  table     where    id    = 1\n" +
                "  ;";
        System.out.println(beautify(sql));
    }

    private String beautify(String originalSql) {
        StringBuilder sb = new StringBuilder(originalSql.length());
        char[] chars = originalSql.toCharArray();
        boolean lastIsWhiteSpace = true;
        for (char aChar : chars) {
            if (aChar == '\n') {
                aChar = ' ';
            }
            boolean whitespace = Character.isWhitespace(aChar);
            if (lastIsWhiteSpace && whitespace) {
                lastIsWhiteSpace = true;
                continue;
            }
            lastIsWhiteSpace = whitespace;
            sb.append(aChar);
        }
        return sb.toString();
    }
}
