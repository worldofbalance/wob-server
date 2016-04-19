/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.targetgenerator;

/**
 *
 * @author Harjit
 */
public class DBStructs {
    
    public class ConsumeStruct{
        public int species_id;
        public int prey;
    }
    
    public class SpeciesStruct{
        public String name;
        public int species_id;
        public int node_id;
        public double biomass;
        public int organism_type;
    }
    
    public ConsumeStruct GetEmptyConsumeStruct(){
        return new ConsumeStruct();
    }
    
    public SpeciesStruct GetEmptySpeciesStruct(){
        return new SpeciesStruct();
    }
}
