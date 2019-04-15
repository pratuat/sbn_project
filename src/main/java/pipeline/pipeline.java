package pipeline;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import twitter4j.TwitterException;
import index.indexTweets;

public class pipeline {

        public static void createTweetIndex(String sourcePath) {

            System.out.println("Tweets Index Creation!");
            String indexPath = "index/indexTweets";
            // Initialize a new TweetsIndexBuilder
            indexTweets indexAllTweets = new indexTweets(sourcePath, indexPath);
            Path dir = Paths.get("index/indexTweets");
            if (!Files.exists(dir)) {
                try {
                    // Build the index
                    indexAllTweets.build();
                } catch (IOException ex) {
                    System.out.println("---> Problems with source files: IOException <---");
                    ex.printStackTrace();
                } catch (TwitterException ex) {
                    System.out.println("---> Problems with Tweets: TwitterException <---");
                    ex.printStackTrace();
                }
            } else {
                // Advise the index already exist
                System.out.println(dir.toString() + ": Index already created!");
            }
        }

        public static void main(String[] args){
            String source = "./src/util/data/stream";
            createTweetIndex(source);
        }

    }
