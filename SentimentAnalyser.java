
import java.io.*;
import java.util.*;

import edu.stanford.nlp.coref.CorefCoreAnnotations;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;


public class SentimentAnalyser {

  public Properties props = new Properties();
  public StanfordCoreNLP pipeline;

  public SentimentAnalyser(){
    props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
    pipeline = new StanfordCoreNLP(props);
  }

  public float Analyse(String text){
    float totalSentiment = 0;
    float sum = 0;
    Annotation annotation = pipeline.process(line);
    for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
      Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
      int totalSentiment += RNNCoreAnnotations.getPredictedClass(tree);
      int sum += 1;
    }
    if (sum == 0){
      return 2.0;
    }
    return (totalSentiment/sum);
  }

    
}