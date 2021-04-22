package hr.tvz.juresic.vaxapp;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JDBCVaccineRepository implements VaccineRepository{

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert vaccineInserter;

    public JDBCVaccineRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.vaccineInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Vaccine");
    }

    @Override
    public List<Vaccine> findAll() {
        return jdbc.query("SELECT * FROM Vaccine;",
                this::mapRowToVaccine);
    }

    @Override
    public Vaccine saveVaccine(Vaccine newVaccine) {
        //SPREMA NOVO CJEPIVO
        Map<String, Object> values = new HashMap<String, Object>();

        values.put("researchName", newVaccine.getResearchName());
        values.put("manufacturerName", newVaccine.getManufacturerName());
        values.put("vaccineType", newVaccine.getVaccineType().toString());
        values.put("numberOfDoses", newVaccine.getNumberOfDoses());
        values.put("availableDoses", newVaccine.getAvailableDoses());

        vaccineInserter.execute(values);

        //DOHVACA SPREMLJENO NOVO CJEPIVO
        Vaccine newlySavedVaccine = jdbc.queryForObject("SELECT * FROM Vaccine WHERE researchName = ?",
                this::mapRowToVaccine, newVaccine.getResearchName());

        return newlySavedVaccine;
    }

    @Override
    public Vaccine updateVaccine(String researchName, Vaccine updatedVaccine) {
        //IZMJENJUJE CJEPIVO
        String updateQuery = "UPDATE Vaccine " +
                "SET manufacturerName = ?, " +
                "vaccineType = ?, " +
                "numberOfDoses = ?, " +
                "availableDoses = ? " +
                "WHERE researchName = ? ;";

        jdbc.update(updateQuery, updatedVaccine.getManufacturerName(), updatedVaccine.getVaccineType().toString(), updatedVaccine.getNumberOfDoses(), updatedVaccine.getAvailableDoses(), researchName);

        //DOHVACA IZMJENJENO CJEPIVO
        Vaccine updateVaccine = jdbc.queryForObject("SELECT * FROM Vaccine WHERE researchName = ?",
                this::mapRowToVaccine, researchName);

        return updateVaccine;
    }

    @Override
    public Optional<Vaccine> findVaccineByResearchName(String researchName) {
        Vaccine vaccine = jdbc.queryForObject("SELECT * FROM Vaccine WHERE researchName = ?;",
                this::mapRowToVaccine, researchName);

        Optional<Vaccine> optionalVaccine = Optional.ofNullable(vaccine);
        return optionalVaccine;

        // ILI KRACE
        /*
        return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM Vaccine WHERE researchName = ?",
                this::mapRowToVaccine, researchName));
        */
    }

    @Override
    public Integer deleteVaccine(String researchName) {
        String deleteQuery = "DELETE FROM Vaccine WHERE researchName = ?;";
        return jdbc.update(deleteQuery, researchName);

    }

    private Vaccine mapRowToVaccine(ResultSet rs, int rowNum) throws SQLException {
        Vaccine vaccine = new Vaccine();

        vaccine.setResearchName(rs.getString("researchName"));
        vaccine.setManufacturerName(rs.getString("manufacturerName"));
        vaccine.setVaccineType(Vaccine.VaccineType.valueOf(rs.getString("vaccineType")));
        vaccine.setNumberOfDoses(rs.getInt("numberOfDoses"));
        vaccine.setAvailableDoses(rs.getInt("availableDoses"));

        return vaccine;
    }

    @Override
    public List<Vaccine> findVaccineWhichBegginsWith(String researchName) {
        String sqlQuery = "SELECT * FROM Vaccine WHERE LOWER(Vaccine.researchName) LIKE ?";
        return jdbc.query(sqlQuery, this::mapRowToVaccine, (researchName.toLowerCase() + "%"));
    }
}
