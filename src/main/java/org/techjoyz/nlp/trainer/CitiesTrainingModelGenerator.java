package org.techjoyz.nlp.trainer;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.io.InputStream;

public class CitiesTrainingModelGenerator {

    private static final String inputDataFile = "cities1000.txt";

    public static void main(String [] args) {
//        try(InputStream inputDataAsStream = CitiesTrainingModelGenerator.class.getResourceAsStream("/cities1000.txt");
//            PrintWriter out = new PrintWriter(new OutputStreamWriter(
//                new BufferedOutputStream(new FileOutputStream("cities1000-place.txt")), "UTF-8"))) {
//            Scanner lineScanner = new Scanner(inputDataAsStream);
//            lineScanner.useDelimiter("\n");
//            int breakAt = 0;
//            while (lineScanner.hasNext()) {
//
//                String newLine = lineScanner.next();
//               // System.out.println(newLine);
//                String[] tokens = newLine.split("\t");
//                StringBuilder locationNameBuilder = new StringBuilder();
//                for (int index = 0; index < tokens.length ; index++) {
//                    if (index == 1) {
//                        locationNameBuilder.append("<START:place> ");
//                        locationNameBuilder.append(tokens[1]);
//                        locationNameBuilder.append(" <END> ");
//                        continue;
//                    }
//                    locationNameBuilder.append(tokens[index]);
//                    locationNameBuilder.append(" ");
//                }
//                out.println(locationNameBuilder.toString());
//                breakAt++;
//
//
//
//            }
//
//        } catch (Exception e) {
//
//        }



//        InputStreamFactory in = null;
//        try {
//            in = new MarkableFileInputStreamFactory(new File("/Users/sbhuin/personal/code/opensource/nlp-ner-trainer/src/main/resources/cities1000-training.txt"));
//        } catch (FileNotFoundException e2) {
//            e2.printStackTrace();
//        }
//
//        ObjectStream sampleStream = null;
//        TokenNameFinderModel nameFinderModel = null;
//        try {
//            sampleStream = new NameSampleDataStream(
//                    new PlainTextByLineStream(in, StandardCharsets.UTF_8));
//            TrainingParameters params = new TrainingParameters();
//            params.put(TrainingParameters.ITERATIONS_PARAM, 70);
//            params.put(TrainingParameters.CUTOFF_PARAM, 1);
//            try {
//                nameFinderModel = NameFinderME.train("en", "location", sampleStream,
//                        params, TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BioCodec()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            File output = new File("ner-location-model.bin");
//            FileOutputStream outputStream = new FileOutputStream(output);
//            nameFinderModel.serialize(outputStream);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }


        // testing the model and printing the types it found in the input sentence
        try {
            InputStream is = CitiesTrainingModelGenerator.class.getResourceAsStream("/ner-location-model.bin");

            TokenNameFinderModel model = new TokenNameFinderModel(is);
            is.close();

            // feed the model to name finder class
            NameFinderME nameFinder = new NameFinderME(model);

            // input string array
            String[] sentence = new String[]{
                    "Encamp",
                    "Barcelona",
                    "is",
                    "from",
                    "Atlanta",
                    "."
            };

            Span nameSpans[] = nameFinder.find(sentence);

            // nameSpans contain all the possible entities detected
            for(Span s: nameSpans){
                System.out.print(s.toString());
                System.out.print("  :  ");
                // s.getStart() : contains the start index of possible name in the input string array
                // s.getEnd() : contains the end index of the possible name in the input string array
                for(int index=s.getStart();index<s.getEnd();index++){
                    System.out.print(sentence[index]+" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
