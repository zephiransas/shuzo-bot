package db.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class V3_1__insert_word_positive_negative implements SpringJdbcMigration {


    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("db/migration/seed/pn.csv.m3.120408.trim")))) {

            String str;
            List<Object[]> batchArgs = new ArrayList<>();

            while ((str = br.readLine()) != null) {
                String[] split = str.split("\t"); // タブで分割
                if (split.length > 1) {
                    String emotion = split[1].trim(); // p or e or n
                    batchArgs.add(new Object[]{
                            split[0].trim(),
                            emotion.equals("p") ? 1 : -1});
                }
            }
            String sql = "REPLACE INTO WordPositiveNegative(word, score) VALUES (?,?)";
            jdbcTemplate.batchUpdate(sql, batchArgs);
        }
    }
}
