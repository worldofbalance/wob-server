package simulation;

import core.ServerResources;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metadata.Constants;

import org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingNode;
import org.datacontract.schemas._2004._07.ManipulationParameter.ManipulatingParameter;
import org.datacontract.schemas._2004._07.ManipulationParameter.NodeBiomass;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationResponse;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfo;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfoRequest;
import org.datacontract.schemas._2004._07.WCFService_Portal.ManipulationTimestepInfoResponse;
import simulation.SpeciesZoneType.SpeciesTypeEnum;
import simulation.config.ManipulatingParameterName;
import simulation.config.ManipulationActionType;
import simulation.config.ModelType;

public class SimulationTest {
    
    private SimulationEngine simulationEngine;
    private Map<Integer, SpeciesZoneType> mSpecies;
    
    public SimulationTest(SimulationEngine simulationEngine) {
        this.simulationEngine = simulationEngine;
        mSpecies = new HashMap<Integer, SpeciesZoneType>();
    }

    public Map<Integer, SpeciesZoneType> getPredictionTest(
            SpeciesZoneType species, 
            String networkOrManipulationId, 
            int currentTimestep
    ) throws SimulationException {
        //Get previous timestep biomass for all species from web service

        if (species != null) {
            String errMsg = simulationEngine.addSpeciesType(
                    species, 
                    null, //jtc - hashmap, szt
                    currentTimestep, 
                    false, 
                    networkOrManipulationId
            );
            if (errMsg == null) {
                mSpecies.put(species.getNodeIndex(), species);
            }
        } else {
            simulationEngine.run(currentTimestep, 1, networkOrManipulationId, false);
        }

//        if (mSpecies.isEmpty()) {
        try {
            simulationEngine.getBiomass(networkOrManipulationId, 0, currentTimestep);
        } catch (SimulationException ex) {
            System.out.println(ex.getMessage());
        }
//        }


        List<NodeBiomass> lNodeBiomass = new ArrayList<NodeBiomass>();


        for (SpeciesZoneType s : mSpecies.values()) {
            s.setBiomassUpdated(false);
            lNodeBiomass.add(new NodeBiomass(s.getCurrentBiomass(), s.getNodeIndex()));
        }

        if (!lNodeBiomass.isEmpty()) {
            try {
                simulationEngine.updateBiomass(networkOrManipulationId, lNodeBiomass, currentTimestep);
            } catch (SimulationException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // get new predicted biomass
//        getBiomass(networkOrManipulationId, 0, currentTimestep);

        return mSpecies;
    }

    public void getPredictionTest(String foodwebName) throws SimulationException  {
        ManipulationResponse response = simulationEngine.createDefaultSubFoodweb(foodwebName);
        String manipulationId = response.getManipulationId();
        String netId = response.getNetworksId();

        getPredictionTest(null, manipulationId, 1);

        SpeciesZoneType szt3 = new SpeciesZoneType("test6", 8, 3, 20, 8.0, SpeciesTypeEnum.ANIMAL);
        getPredictionTest(szt3, manipulationId, 2);

        getPredictionTest(null, manipulationId, 3);

        SpeciesZoneType s = mSpecies.get(5);
        s.setCurrentBiomass(1000.0);
        s = mSpecies.get(88);
        s.setCurrentBiomass(1.0);

        SpeciesZoneType szt4 = new SpeciesZoneType("test7", 11, 3, 20, 8.0, SpeciesTypeEnum.ANIMAL);
        getPredictionTest(szt4, manipulationId, 4);

        simulationEngine.getBiomassInfo(manipulationId);
        simulationEngine.deleteManipulation(manipulationId);

    }

    public String foodwebStabilityTest10_1_1() throws SimulationException  {
        int nodeList[] = {1, 2, 3, 5, 8, 9, 12, 15, 20, 26};
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test1_2111", 0, 5, true);


        SpeciesZoneType szt1 = new SpeciesZoneType("", 10, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
        //null, //jtc - hashmap, szt                 
        manpId = simulationEngine.addSpeciesType(szt1, null, 6, false, manpId);
        if (manpId == null) {
            return null;
        }
        szt1 = new SpeciesZoneType("", 11, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
        manpId = simulationEngine.addSpeciesType(szt1, null, 7, false, manpId);
        if (manpId == null) {
            return null;
        }
        szt1 = new SpeciesZoneType("", 13, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
        manpId = simulationEngine.addSpeciesType(szt1, null, 8, false, manpId);
        if (manpId == null) {
            return null;
        }
        szt1 = new SpeciesZoneType("", 14, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
        manpId = simulationEngine.addSpeciesType(szt1, null, 9, false, manpId);
        if (manpId == null) {
            return null;
        }
        szt1 = new SpeciesZoneType("", 16, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
        manpId = simulationEngine.addSpeciesType(szt1, null, 10, false, manpId);
        if (manpId == null) {
            return null;
        }

//            getBiomassInfo(manpId);
        return manpId;
    }

    public String foodwebStabilityTest3(String fwname) throws SimulationException {
        int nodeList[] = {7, 33};
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, fwname, 0, 3, true);

        HashMap<Integer, SpeciesZoneType> spList = new HashMap<Integer, SpeciesZoneType>();
        SpeciesZoneType szt1 = new SpeciesZoneType("", 10, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
        spList.put(szt1.getNodeIndex(), szt1);
        szt1 = new SpeciesZoneType("", 11, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
        spList.put(szt1.getNodeIndex(), szt1);
        szt1 = new SpeciesZoneType("", 12, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
        spList.put(szt1.getNodeIndex(), szt1);

        try {
            simulationEngine.addMultipleSpeciesType(spList, spList, 3, false, manpId);
        } catch (SimulationException ex) {
            simulationEngine.deleteManipulation(manpId);
            return "addMultipleSpeciesType failed";
        }

        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.deleteManipulation(manpId);
        return null;

        // ran 30 timesteps without any exinction
    }

    public void foodwebStabilityTest10_1() throws SimulationException {
        int nodeList[] = {3, 4, 5, 7, 86, 87, 88, 89, 91, 93};
        // Fruits and nectar, Grains and seeds, Grass and herbs,  Trees and shrubs, Lion, Topi, Buffalo, Southern eland, Nile crocodile, Black Rhinoceros
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test1", 0, 100, true);

        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.deleteManipulation(manpId);

        /*
         Node[3] + Node name[Fruits and nectar] +Timestep [98]:0.01977582462131977
         Node[4] + Node name[Grains, seeds] +Timestep [98]:0.01977582462131977
         Node[5] + Node name[Grass and herbs] +Timestep [98]:1.8838178948499262E-4
         Node[7] + Node name[Trees and shrubs] +Timestep [98]:3.52768343873322E-4
         Node[86] + Node name[Lion] +Timestep [98]:0.22752830386161804
         Node[87] + Node name[Topi] +Timestep [98]:3.082611943483471E-11
         Node[88] + Node name[Buffalo] +Timestep [98]:1.1597672822105665E-10
         Node[89] + Node name[Southern eland] +Timestep [98]:0.666655957698822
         Node[91] + Node name[Nile crocodile] +Timestep [98]:9.649700950831175E-5
         Node[93] + Node name[Black rhinoceros] +Timestep [98]:0.17299097776412964            
         */

        // ran 100 timesteps without any exinction
    }

    public void foodwebStabilityTest10_2() throws SimulationException {
        int nodeList[] = {3, 4, 5, 7, 86, 87, 88, 89, 91, 92};
        // Fruits and nectar, Grains and seeds, Grass and herbs,  Trees and shrubs, Lion, Topi, Buffalo, Southern eland, Nile crocodile, Maasai Giraffe            
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test8", 0, 1, true);
        System.out.println("manpId:" + manpId);
        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.deleteManipulation(manpId);

        // ran 50 timesteps without any exinction
          /*
         Node[3] + Node name[Fruits and nectar] +Timestep [98]:0.04633544012904167
         Node[4] + Node name[Grains, seeds] +Timestep [98]:0.04633544012904167
         Node[5] + Node name[Grass and herbs] +Timestep [98]:0.008385155349969864
         Node[7] + Node name[Trees and shrubs] +Timestep [98]:1.3869999384041876E-4
         Node[86] + Node name[Lion] +Timestep [98]:0.47203147411346436
         Node[87] + Node name[Topi] +Timestep [98]:2.495455575513006E-8
         Node[88] + Node name[Buffalo] +Timestep [98]:0.004852019250392914
         Node[89] + Node name[Southern eland] +Timestep [98]:0.08423218131065369
         Node[91] + Node name[Nile crocodile] +Timestep [98]:9.602162754163146E-5
         Node[92] + Node name[Maasai giraffe] +Timestep [98]:1.0182245023315772E-4
         */
    }

    public void foodwebStabilityTest10_3() throws SimulationException  {
        int nodeList[] = {3, 4, 5, 7, 86, 87, 88, 89, 91, 94};
        // Fruits and nectar, Grains and seeds, Grass and herbs,  Trees and shrubs, Lion, Topi, Buffalo, Southern eland, Nile crocodile, Hippopotamus      
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test3", 0, 1, true);


        getPredictionTest(null, manpId, 1);


        SpeciesZoneType s = mSpecies.get("Fruits and nectar");
        s.setCurrentBiomass(1000.0);
        s = mSpecies.get("Grains, seeds");
        s.setCurrentBiomass(1000.0);
        s = mSpecies.get("Grass and herbs");
        s.setCurrentBiomass(1000.0);
        s = mSpecies.get("Trees and shrubs");
        s.setCurrentBiomass(1000.0);
        s = mSpecies.get("Lion");
        s.setCurrentBiomass(1.0);
        s = mSpecies.get("Topi");
        s.setCurrentBiomass(3.0);
        s = mSpecies.get("Buffalo");
        s.setCurrentBiomass(30.0);
        s = mSpecies.get("Southern eland");
        s.setCurrentBiomass(20.0);
        s = mSpecies.get("Nile crocodile");
        s.setCurrentBiomass(2.0);
        s = mSpecies.get("Hippopotamus");
        s.setCurrentBiomass(3.0);
        getPredictionTest(null, manpId, 2);
        simulationEngine.run(3, 97, manpId, false);

        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.deleteManipulation(manpId);



        // ran 100 timesteps without any exinction
           /*            
         Node[3] + Node name[Fruits and nectar] +Timestep [98]:0.10086638480424881
         Node[4] + Node name[Grains, seeds] +Timestep [98]:0.10086638480424881
         Node[5] + Node name[Grass and herbs] +Timestep [98]:9.615123417461291E-5
         Node[7] + Node name[Trees and shrubs] +Timestep [98]:0.10084232687950134
         Node[86] + Node name[Lion] +Timestep [98]:0.3669547438621521
         Node[87] + Node name[Topi] +Timestep [98]:3.273458730745915E-7
         Node[88] + Node name[Buffalo] +Timestep [98]:1.2872754077963844E-13
         Node[89] + Node name[Southern eland] +Timestep [98]:0.021927716210484505
         Node[91] + Node name[Nile crocodile] +Timestep [98]:9.950158710125834E-5
         Node[94] + Node name[Hippopotamus] +Timestep [98]:8.20449786260724E-5            
         */

    }

    public String foodwebModelStabilityTest1() throws SimulationException {
        int nodeList[] = {5, 7, 33, 52, 80, 82, 86, 92, 95};
        // Fruits and nectar, Grains and seeds, Grass and herbs,  Trees and shrubs, Leopard, Lion, Topi, Buffalo, Southern eland, Nile crocodile
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sgModelTest4", 0, 5, true);

        List<NodeBiomass> lNodeBiomass = new ArrayList<NodeBiomass>();
        NodeBiomass nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(5);
        lNodeBiomass.add(nb);
        nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(7);
        lNodeBiomass.add(nb);
        nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(33);
        lNodeBiomass.add(nb);
        nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(52);
        lNodeBiomass.add(nb);
        nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(80);
        lNodeBiomass.add(nb);
        nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(82);
        lNodeBiomass.add(nb);
        nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(86);
        lNodeBiomass.add(nb);
        nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(92);
        lNodeBiomass.add(nb);
        nb = new NodeBiomass();
        nb.setBiomass(0);
        nb.setNodeIdx(95);
        lNodeBiomass.add(nb);

        try {
            simulationEngine.updateBiomass(manpId, lNodeBiomass, 6);
        } catch (SimulationException ex) {
            return null;
        }

        return manpId;
    }

    public String foodwebStabilityTest10_4() {
        try {
            int nodeList[] = {3, 4, 5, 7, 80, 86, 87, 88, 89, 91};
            // Fruits and nectar, Grains and seeds, Grass and herbs,  Trees and shrubs, Leopard, Lion, Topi, Buffalo, Southern eland, Nile crocodile
            String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test4", 0, 1, true);


            List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
            ManipulatingNode node = new ManipulatingNode();
            node.setTimestepIdx(1);
            node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
            node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
            node.setNodeIdx(5);
            node.setBeginingBiomass(500);
            node.setHasLinks(false);
            nodes.add(node);

            List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
            simulationEngine.setNodeParameter(5, ManipulatingParameterName.k.getManipulatingParameterIndex(), 1000, sParams);
//            simulationEngine.setLinkParameter(88, 5, ManipulatingParameterName.e.getManipulatingParameterIndex(), 0.8, sParams);

            simulationEngine.updateSystemParameters(1, false, manpId, sParams, nodes);

            simulationEngine.run(2, 5, manpId, false);

            return manpId;
        } catch (Exception e) {
            return null;
        }
    }

    public void testManpThread() {
        String fwName = "fwManpConcurrentTest";
        int i = 0;
        for (i = 0; i < 20; i++) {
            (new ManplTheadTest(fwName + (i++))).run();
        }
    }

    public class ManplTheadTest implements Runnable {

        String name = null;

        ManplTheadTest(String fwName) {
            name = fwName;
        }

        public void run() {
            try {
                String msg = foodwebStabilityTest3(name);
                if (msg != null) {
                    System.out.println("Concurrent manipulation [" + name + "] failed");
                }
            } catch (Exception e) {
                System.err.println("Error:" + e.getMessage());
            }

        }
    }

    public void foodwebStabilityTest10_5() throws SimulationException {
//            int nodeList[] = {3, 4, 5, 7, 80, 86, 87, 88, 89, 91};
        int nodeList[] = {4, 5, 73};
        // Fruits and nectar, Grains and seeds, Grass and herbs,  Trees and shrubs, Leopard, Lion, Topi, Buffalo, Southern eland, Nile crocodile
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test27", 0, 1, true);
        System.out.println("manpId:" + manpId);

        List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
        ManipulatingNode node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(4);
        node.setBeginingBiomass(500);
        node.setHasLinks(false);
        nodes.add(node);


        node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(500);
        node.setHasLinks(false);
        nodes.add(node);


        node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(73);
        node.setBeginingBiomass(80);
        node.setHasLinks(false);
        nodes.add(node);
        /*
         node = new ManipulatingNode();
         node.setTimestepIdx(1);
         node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
         node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
         node.setNodeIdx(73);
         node.setBeginingBiomass(100);
         node.setHasLinks(false);
         nodes.add(node);
         */


        node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(80);
        node.setBeginingBiomass(30);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);


        List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(5, ManipulatingParameterName.k.getManipulatingParameterIndex(), 1000, sParams);
        simulationEngine.setNodeParameter(4, ManipulatingParameterName.k.getManipulatingParameterIndex(), 1000, sParams);
//            simulationEngine.setNodeParameter(5, ManipulatingParameterName.r.getManipulatingParameterIndex(), 2, sParams);                        
//            simulationEngine.setNodeParameter(5, ManipulatingParameterName.r.getManipulatingParameterIndex(), 8, sParams);            
//            simulationEngine.setNodeParameter(73, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.8, sParams);            
//            simulationEngine.setLinkParameter(88, 5, ManipulatingParameterName.e.getManipulatingParameterIndex(), 0.8, sParams);

        simulationEngine.updateSystemParameters(1, false, manpId, sParams, nodes);

//            nodes = new ArrayList<ManipulatingNode>();
//            sParams = new ArrayList<ManipulatingParameter>();
//            simulationEngine.updateSystemParameters(4, 1, false, manpId, sParams, nodes);            





        simulationEngine.run(2, 18, manpId, false);

        /*            
         nodes = new ArrayList<ManipulatingNode>();
         node = new ManipulatingNode();
         node.setTimestepIdx(11);
         node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
         node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
         node.setNodeIdx(5);
         node.setBeginingBiomass(100);
         node.setHasLinks(false);
         nodes.add(node);
         sParams = new ArrayList<ManipulatingParameter>();
  
         simulationEngine.updateSystemParameters(10, false, manpId, sParams, nodes);            

         simulationEngine.run(11, 3, manpId, false);
         //            sParams = new ArrayList<ManipulatingParameter>();
         //            simulationEngine.setNodeParameter(7, ManipulatingParameterName.k.getManipulatingParameterIndex(), 8000, sParams);
         //            simulationEngine.updateSystemParameters(6, false, manpId, sParams, nodes);            
            
            
         */

//          simulationEngine.run(7, 10, manpId, false);


        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.deleteManipulation(manpId);
        // ran 100 timesteps without any exinction
           /*            
         Node[3] + Node name[Fruits and nectar] +Timestep [98]:0.10086638480424881
         Node[4] + Node name[Grains, seeds] +Timestep [98]:0.10086638480424881
         Node[5] + Node name[Grass and herbs] +Timestep [98]:9.615123417461291E-5
         Node[7] + Node name[Trees and shrubs] +Timestep [98]:0.10084232687950134
         Node[86] + Node name[Lion] +Timestep [98]:0.3669547438621521
         Node[87] + Node name[Topi] +Timestep [98]:3.273458730745915E-7
         Node[88] + Node name[Buffalo] +Timestep [98]:1.2872754077963844E-13
         Node[89] + Node name[Southern eland] +Timestep [98]:0.021927716210484505
         Node[91] + Node name[Nile crocodile] +Timestep [98]:9.950158710125834E-5
         Node[94] + Node name[Hippopotamus] +Timestep [98]:8.20449786260724E-5            
         */

    }

    public String runningTest() throws SimulationException  {
        int nodeList[] = {4, 5, 73};
        // Fruits and nectar, Grains and seeds, Grass and herbs,  Trees and shrubs, Leopard, Lion, Topi, Buffalo, Southern eland, Nile crocodile
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test27111121", 0, 1, true);
        for (int i = 1; i < 15; i++) {
            SpeciesZoneType szt = new SpeciesZoneType("", i + 7, 1, 1, 1.0, SpeciesTypeEnum.ANIMAL);
            //null, //jtc - hashmap szt
            String result = simulationEngine.addSpeciesType(
                    szt, 
                    null, 
                    i, 
                    false, 
                    manpId
            );
            if (result == null) {
                return null;
            }
//                simulationEngine.run(i, 1, manpId);
        }
        return manpId;

    }

    public void foodwebStabilityTest10_6() throws SimulationException {
//            int nodeList[] = {3, 4, 5, 7, 80, 86, 87, 88, 89, 91};
        int nodeList[] = {4, 5};
        // Fruits and nectar, Grains and seeds, Grass and herbs,  Trees and shrubs, Leopard, Lion, Topi, Buffalo, Southern eland, Nile crocodile
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test27", 0, 1, true);
        System.out.println("manpId:" + manpId);

        List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
        ManipulatingNode node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(4);
        node.setBeginingBiomass(500);
        node.setHasLinks(false);
        nodes.add(node);


        node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(500);
        node.setHasLinks(false);
        nodes.add(node);



        node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(80);
        node.setBeginingBiomass(30);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);


        List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(5, ManipulatingParameterName.k.getManipulatingParameterIndex(), 1000, sParams);
        simulationEngine.setNodeParameter(4, ManipulatingParameterName.k.getManipulatingParameterIndex(), 1000, sParams);

        simulationEngine.updateSystemParameters(1, false, manpId, sParams, nodes);

        simulationEngine.run(2, 18, manpId, false);


        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.deleteManipulation(manpId);
    }

    public void foodwebStabilityTest10_7() throws SimulationException {

        int nodeList[] = {4, 5, 73};

        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "sg10Test27", 0, 1, true);
        System.out.println("manpId:" + manpId);

        List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
        ManipulatingNode node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(4);
        node.setBeginingBiomass(2500);
        node.setHasLinks(false);
        nodes.add(node);


        node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2500);
        node.setHasLinks(false);
        nodes.add(node);


        node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(73);
        node.setBeginingBiomass(80);
        node.setHasLinks(false);
        nodes.add(node);

        /*            
         node = new ManipulatingNode();
         node.setTimestepIdx(1);
         node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
         node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
         node.setNodeIdx(80);
         node.setBeginingBiomass(30);
         node.setHasLinks(false);
         node.setGameMode(true);            
         node.setOriginFoodwebId(propertiesConfig.getProperty("serengetiNetworkId"));            
         nodes.add(node);                   
         */

        List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(5, ManipulatingParameterName.k.getManipulatingParameterIndex(), 8000, sParams);
        simulationEngine.setNodeParameter(4, ManipulatingParameterName.k.getManipulatingParameterIndex(), 8000, sParams);

        simulationEngine.updateSystemParameters(1, false, manpId, sParams, nodes);


        simulationEngine.run(2, 18, manpId, false);

        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.deleteManipulation(manpId);

    }

    public void runManipulationTest(String manpId, int startTS, int tsToRun) {
        simulationEngine.run(startTS, tsToRun, manpId, false);


        simulationEngine.getBiomassInfo(manpId);
//            deleteManipulation(manpId);              

    }

    public String getBiomassTest(String manipulationId, int nodeIndex, int timestep) {

        if (timestep >= 0) {
            ManipulationTimestepInfoRequest req = new ManipulationTimestepInfoRequest();
            req.setManipulationId(manipulationId);
            req.setIsNodeTimestep(true);
            req.setNodeIdx(nodeIndex);
            req.setTimestep(timestep);

            ManipulationTimestepInfoResponse response = null;
            try {
                response = (ManipulationTimestepInfoResponse) simulationEngine.getService().executeRequest(req);
            } catch (RemoteException e) {
                e.printStackTrace();
                return "error on running ManipulationTimestepInfoRequest:" + e.getMessage();
            }
            ManipulationTimestepInfo[] infos = response.getManipulationTimestepInfos();
            //TODO: Write web service call to database

            if (infos.length > 0) {

                try {
                    for (ManipulationTimestepInfo info : infos) {
                        System.out.println("Node[" + info.getNodeIdx() + "][" + info.getNodeName() + "][" + info.getTimestepIdx() + "]:" + info.getBiomass());
                    }

                } catch (Exception e) {
                    return "Error on getBiomass :" + e.getMessage();
                }
            } else {
                mSpecies.clear();
            }
        } else {
            return "Error : timestep is below 0";
        }


        return null;
    }

    public void testGetBiomass() {


        System.out.println(getBiomassTest("2c13f25d-3733-49a3-bfbf-503f83094778", 0, 37));


    }

    public boolean isSameParameter(ManipulatingParameter oParam, ManipulatingParameter dParam) {
        if ((oParam.getNodeIdx() == dParam.getNodeIdx() && oParam.getTimestepIdx() == dParam.getTimestepIdx()
                || (oParam.getPredIdx() == dParam.getPredIdx() && oParam.getPreyIdx() == dParam.getPreyIdx()) && oParam.getTimestepIdx() == dParam.getTimestepIdx())) {
            if (oParam.getParamIdx() == dParam.getParamIdx() && oParam.getParamType() == dParam.getParamType() && oParam.getParamValue() == dParam.getParamValue()) {
                return true;
            }
        }

        return false;
    }

    public boolean testParameters(String manpId, List<ManipulatingParameter> sParams) {
        int count = sParams.size();
        int matchCount = 0;
//        ManipulatingParameter[] params = simulationEngine.getSystemParameterInfos(manpId);
//           if(sParams.size() != params.length)
//        for (ManipulatingParameter sParam : sParams) {
//            for (ManipulatingParameter dParam : params) {
//                if (isSameParameter(sParam, dParam)) {
//                    matchCount++;
//                }
//            }
//        }
        if (matchCount == count) {
            return true;
        } else {
            return false;
        }
    }

    public void testNode4Oribi() throws SimulationException {
        int nodeList[] = {1, 9};
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "testNode4and73", 0, 1, true);

        List<ManipulatingNode> nodes = new ArrayList<ManipulatingNode>();
        ManipulatingNode node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);


        node = new ManipulatingNode();
        node.setTimestepIdx(1);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(9);
        node.setBeginingBiomass(10);
        node.setHasLinks(false);
        nodes.add(node);


        List<ManipulatingParameter> sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(1, ManipulatingParameterName.k.getManipulatingParameterIndex(), 4000, sParams);
        simulationEngine.setNodeParameter(9, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.75, 1, sParams);
//            simulationEngine.setNodeParameter(12, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.9, sParams);                        
//            simulationEngine.setNodeParameter(5, ManipulatingParameterName.k.getManipulatingParameterIndex(), 5000, sParams);            
//            simulationEngine.setNodeParameter(4, ManipulatingParameterName.r.getManipulatingParameterIndex(), , sParams);
//            simulationEngine.setNodeParameter(5, ManipulatingParameterName.r.getManipulatingParameterIndex(), 0.333, sParams);            

//            simulationEngine.setLinkParameter(73, 4, ManipulatingParameterName.a.getManipulatingParameterIndex(), 0.6, 1, sParams);            
//            simulationEngine.setLinkParameter(73, 5, ManipulatingParameterName.a.getManipulatingParameterIndex(), 0.4, 1, sParams);                        
        simulationEngine.updateSystemParameters(1, false, manpId, sParams, nodes);

///            testParameters(manpId, sParams);
//            run(2, 8, manpId, false);                        


        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(2);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(12);
        node.setBeginingBiomass(10);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(12, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.85, 2, sParams);
//            simulationEngine.setLinkParameter(9, 1, ManipulatingParameterName.a.getManipulatingParameterIndex(), 0.05, 1, sParams);            
//            simulationEngine.setLinkParameter(9, 9, ManipulatingParameterName.a.getManipulatingParameterIndex(), 0.3, 1, sParams);                        
//            simulationEngine.setLinkParameter(9, 12, ManipulatingParameterName.a.getManipulatingParameterIndex(), 0.65, 1, sParams);                        

//            simulationEngine.setLinkParameter(73, 4, ManipulatingParameterName.d.getManipulatingParameterIndex(), 1, 3, sParams);
//            simulationEngine.setNodeParameter(73, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.9, sParams);
//            simulationEngine.setNodeParameter(5, ManipulatingParameterName.r.getManipulatingParameterIndex(), 0.333, sParams);            
        simulationEngine.updateSystemParameters(2, false, manpId, sParams, nodes);
//            run(3, 27, manpId, false);                          


        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(3);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(20);
        node.setBeginingBiomass(4);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        node = new ManipulatingNode();
        node.setTimestepIdx(3);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(25);
        node.setBeginingBiomass(5);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        node = new ManipulatingNode();
        node.setTimestepIdx(3);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(20, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.65, 3, sParams);
//            simulationEngine.setLinkParameter(73, 4, ManipulatingParameterName.a.getManipulatingParameterIndex(), 0.6, 1, sParams);            

        simulationEngine.updateSystemParameters(3, false, manpId, sParams, nodes);
//            run(4, 22, manpId, false);                        



        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(4);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(75);
        node.setBeginingBiomass(10);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);


        node = new ManipulatingNode();
        node.setTimestepIdx(4);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(3000);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);



        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(5, ManipulatingParameterName.k.getManipulatingParameterIndex(), 4000, sParams);
        simulationEngine.setNodeParameter(75, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.4, 4, sParams);
        simulationEngine.updateSystemParameters(4, false, manpId, sParams, nodes);



        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(5);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(57);
        node.setBeginingBiomass(10);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        node = new ManipulatingNode();
        node.setTimestepIdx(5);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);
        node = new ManipulatingNode();
        node.setTimestepIdx(5);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);



        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(57, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.35, 5, sParams);
        simulationEngine.updateSystemParameters(5, false, manpId, sParams, nodes);


        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(6);
        node.setManipulationActionType(ManipulationActionType.SPECIES_INVASION.getManipulationActionType()); // invasion
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(49);
        node.setBeginingBiomass(10);
        node.setHasLinks(false);
        node.setGameMode(true);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.setNodeParameter(49, ManipulatingParameterName.x.getManipulatingParameterIndex(), 0.3, 6, sParams);
        simulationEngine.updateSystemParameters(6, false, manpId, sParams, nodes);



        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(7);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);
        sParams = new ArrayList<ManipulatingParameter>();
        node = new ManipulatingNode();
        node.setTimestepIdx(7);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);


        simulationEngine.updateSystemParameters(7, false, manpId, sParams, nodes);


        simulationEngine.run(8, 1, manpId, false);
        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(9);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);
        node = new ManipulatingNode();
        node.setTimestepIdx(9);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.updateSystemParameters(9, false, manpId, sParams, nodes);


        simulationEngine.run(10, 1, manpId, false);
        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(11);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);
        node = new ManipulatingNode();
        node.setTimestepIdx(11);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.updateSystemParameters(11, false, manpId, sParams, nodes);


        simulationEngine.run(12, 1, manpId, false);
        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(13);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);
        node = new ManipulatingNode();
        node.setTimestepIdx(13);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.updateSystemParameters(13, false, manpId, sParams, nodes);


        simulationEngine.run(14, 1, manpId, false);
        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(15);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);
        node = new ManipulatingNode();
        node.setTimestepIdx(15);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.updateSystemParameters(15, false, manpId, sParams, nodes);

        simulationEngine.run(16, 1, manpId, false);
        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(17);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);
        node = new ManipulatingNode();
        node.setTimestepIdx(17);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.updateSystemParameters(17, false, manpId, sParams, nodes);

        simulationEngine.run(18, 1, manpId, false);
        nodes = new ArrayList<ManipulatingNode>();
        node = new ManipulatingNode();
        node.setTimestepIdx(19);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(1);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        nodes.add(node);
        node = new ManipulatingNode();
        node.setTimestepIdx(19);
        node.setManipulationActionType(ManipulationActionType.SPECIES_PROLIFERATION.getManipulationActionType()); // proliferation
        node.setModelType(ModelType.CASCADE_MODEL.getModelType()); // cascading model
        node.setNodeIdx(5);
        node.setBeginingBiomass(2000);
        node.setHasLinks(false);
        node.setOriginFoodwebId(simulationEngine.getProperties().getProperty("serengetiNetworkId"));
        nodes.add(node);

        sParams = new ArrayList<ManipulatingParameter>();
        simulationEngine.updateSystemParameters(19, false, manpId, sParams, nodes);



        simulationEngine.saveBiomassCSVFile(manpId);
        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.deleteManipulation(manpId);

    }

    public void someTest() throws SimulationException {
        int nodeList[] = {5, 7, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 4};
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "testNode4and73", 0, 0, true);

        List<NodeBiomass> lNodeBiomass = new ArrayList<NodeBiomass>();
        for (int node_id : nodeList) {
            lNodeBiomass.add(new NodeBiomass(500 * 1000 / Constants.BIOMASS_SCALE, node_id));
        }

//        if (!lNodeBiomass.isEmpty()) {
//            System.out.println("Updating Initial Biomass...");
//            try {
//                simulationEngine.updateBiomass(manpId, lNodeBiomass, 0);
//            } catch (SimulationException ex) {
//            }
//        }

        simulationEngine.run(1, 200, manpId);

        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.saveBiomassCSVFile(manpId);

        simulationEngine.deleteManipulation(manpId);
    }

    public void someTest1() throws SimulationException {
        int nodeList[] = {5};
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "testNode4and73", 0, 0, true);

        List<NodeBiomass> lNodeBiomass = new ArrayList<NodeBiomass>();
        lNodeBiomass.add(new NodeBiomass(ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(5).getBiomass() * 1000 / Constants.BIOMASS_SCALE, 5));

        if (!lNodeBiomass.isEmpty()) {
            System.out.println("Updating Initial Biomass...");
            try {
                simulationEngine.updateBiomass(manpId, lNodeBiomass, 0);
            } catch (SimulationException ex) {
            }
        }
        
        List<SpeciesZoneType> species = new ArrayList<SpeciesZoneType>();
        SpeciesZoneType szt = simulationEngine.createSpeciesZoneType(5, 1000);
        szt.setParamK(2000);
        species.add(szt);
        simulationEngine.setParameters2(species, 0, manpId);

        simulationEngine.run(1, 10, manpId);

        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.saveBiomassCSVFile(manpId);
        simulationEngine.deleteManipulation(manpId);
    }
    
    public void someTest2() throws SimulationException {
        int nodeList[] = {5};
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "testNode4and73", 0, 0, true);

        List<NodeBiomass> lNodeBiomass = new ArrayList<NodeBiomass>();
        lNodeBiomass.add(new NodeBiomass(ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(5).getBiomass() * 1000 / Constants.BIOMASS_SCALE, 5));

        if (!lNodeBiomass.isEmpty()) {
            System.out.println("Updating Initial Biomass...");
            try {
                simulationEngine.updateBiomass(manpId, lNodeBiomass, 0);
            } catch (SimulationException ex) {
            }
        }
        
        List<SpeciesZoneType> species = new ArrayList<SpeciesZoneType>();
        SpeciesZoneType szt = simulationEngine.createSpeciesZoneType(5, 1000);
        szt.setParamK(30);
        species.add(szt);
        simulationEngine.setParameters2(species, 0, manpId);

        simulationEngine.run(1, 10, manpId);

        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.saveBiomassCSVFile(manpId);

        simulationEngine.deleteManipulation(manpId);
    }
    
    public void someTestCarryingCapacity() throws SimulationException {
        int nodeList[] = {5, 79};
        String manpId = simulationEngine.createAndRunSeregenttiSubFoodweb(nodeList, "testNode4and73", 0, 0, true);
 	
        List<NodeBiomass> lNodeBiomass = new ArrayList<NodeBiomass>();
 	
        lNodeBiomass.add(new NodeBiomass(ServerResources.getSpeciesTable().getSpeciesTypeByNodeID(5).getBiomass() * 1000 / Constants.BIOMASS_SCALE, 5));
 	
        if (!lNodeBiomass.isEmpty()) {
			System.out.println("Updating Initial Biomass...");
			try {
			    simulationEngine.updateBiomass(manpId, lNodeBiomass, 0);
			} catch (SimulationException ex) {
			}
        }
        System.out.println("PARAMETER_K= 1000.0 run 1-10");
        HashMap<Short, Float> parameterList = new HashMap<Short, Float>();
        parameterList.put(((short)Constants.PARAMETER_K), (float) 1000.0);
        
        SpeciesZoneType szt = new SpeciesZoneType();
        szt.setNodeIndex(5);
        szt.setCurrentBiomass(lNodeBiomass.get(0).getBiomass());
        
        HashMap<Integer, SpeciesZoneType> mSpecies = new HashMap<Integer, SpeciesZoneType>();
        mSpecies.put(0,szt);
//        simulationEngine.setParameters(mSpecies, 0, manpId, parameterList );
 	
        simulationEngine.run(1, 30, manpId);
 	
        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.saveBiomassCSVFile(manpId);
        
        System.out.println("PARAMETER_K= 1.0 run 1-10");
        parameterList = new HashMap<Short, Float>();
        parameterList.put(((short)Constants.PARAMETER_K), (float) 1.0);
        
//        simulationEngine.setParameters(mSpecies, 0, manpId, parameterList);
 	
        simulationEngine.run(1, 30, manpId);
 	
        simulationEngine.getBiomassInfo(manpId);
        simulationEngine.saveBiomassCSVFile(manpId);  	        
        
        simulationEngine.deleteManipulation(manpId);
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException, SimulationException {
        SimulationTest sTest = new SimulationTest(new SimulationEngine());
//                SimulationEngine se = new SimulationEngine("http://n3d.cloudapp.net/N3DWebService.svc?wsdl");             
//	    SimulationEngine se = new SimulationEngine("http://localhost:41246/N3DWebService.svc?wsdl");                             
//	    SimulationEngine se = new SimulationEngine("http://127.0.0.1:81/N3DWebService.svc?wsdl");                                         
//        SimulationEngine se = new SimulationEngine("http://54050601f7a9427285bd6fcfd56f8679.cloudapp.net/N3DWebService.svc?wsdl");

        sTest.someTest();
//        sTest.someTest1();
//        sTest.someTest2();
//        sTest.testNode4Oribi();
//                String manpl = se.foodwebStabilityTest10_1_1();
//                se.deleteManipulation(manpl);                
//                 se.foodwebStabilityTest10_5();
/*                
         String manpId = se.runningTest();
         if(manpId != null)
         {
         se.getBiomassInfo(manpId);
         se.saveBiomassCSVFile(manpId);
         se.deleteManipulation(manpId);
         }
         * 
         */
//                se.getBiomassInfo("BC043271-726E-44D1-AA6A-8BD787002AFD"); 
//                se.SixSpeciesFoodwebTest("aFW105");
//                se.getPredictionTest("abc3");
//                se.foodwebStabilityTest3();
//                se.foodwebStabilityTest10_5();
//                se.foodwebStabilityTest10_7();                
//                se.runManipulationTest("20a2ad49-11a4-49f2-91c8-41a5cfe764e4",3,5);
//                se.testGetBiomass();
//                se.foodwebStabilityTest10_5();
//                se.nodeInfoTest();
//                se.SixSpeciesFoodwebTest("aFoodWeb1000");
    }
}
