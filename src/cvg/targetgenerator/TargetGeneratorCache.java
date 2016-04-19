/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.targetgenerator;

import cow.db.GameDB;
import cvg.targetgenerator.DBStructs.ConsumeStruct;
import cvg.targetgenerator.DBStructs.SpeciesStruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Harjit
 */
public class TargetGeneratorCache {

    public static ArrayList<ConsumeStruct> consumes;
    public static ArrayList<SpeciesStruct> species;
    private static DBStructs structs;

    public static void init() {
        consumes = new ArrayList<ConsumeStruct>();
        species = new ArrayList<SpeciesStruct>();
        structs = new DBStructs();
        try {
            structs = new DBStructs();
            Connection conn = GameDB.getConnection();
            GrabConsumesTable(conn);
            GrabSpeciesTable(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }

    private static void GrabConsumesTable(Connection conn) throws Exception {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consume");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int species_id = rs.getInt("species_id");
            int prey_id = rs.getInt("prey_id");
            ConsumeStruct struct = structs.GetEmptyConsumeStruct();
            struct.prey = prey_id;
            struct.species_id = species_id;
            consumes.add(struct);
        }
        stmt.close();
        rs.close();
    }

    private static void GrabSpeciesTable(Connection conn) throws Exception {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM species");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            int species_id = rs.getInt("species_id");
            double mass = rs.getDouble("biomass");
            int organism_type = rs.getInt("organism_type");
            SpeciesStruct struct = structs.GetEmptySpeciesStruct();

            struct.biomass = mass;
            struct.name = name;
            struct.organism_type = organism_type;
            struct.species_id = species_id;
            species.add(struct);
        }
        stmt.close();
        rs.close();

        stmt = conn.prepareStatement("SELECT * FROM species_nodes");
        rs = stmt.executeQuery();

        while (rs.next()) {
            int species_id = rs.getInt("species_id");
            int node_id = rs.getInt("node_id");

            for (int i = 0; i < species.size(); i++) {
                if (species_id == species.get(i).species_id) {
                    species.get(i).node_id = node_id;
                }
            }
        }

        stmt.close();
        rs.close();
    }
}
