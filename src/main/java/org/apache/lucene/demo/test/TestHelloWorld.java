package org.apache.lucene.demo.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Assert;

/**
 * {@link TestHelloWorld}
 */
public class TestHelloWorld {

    public static void main(String[] args) throws Exception {
        // store the index in memory
        Directory directory = new RAMDirectory();
        // To store an index on disk, use this instead:
        //Directory directory = FSDirectory.getDirectory("/tmp/testindex");

        Analyzer analyzer = new StandardAnalyzer();
        IndexWriter iwriter = new IndexWriter(directory, analyzer, true);
        iwriter.setMaxFieldLength(25000);
        Document doc = new Document();
        String text = "This is the text to be indexed.";
        doc.add(new Field("fieldname", text, Field.Store.YES, Field.Index.TOKENIZED));
        iwriter.addDocument(doc);
        iwriter.optimize();
        iwriter.close();

        // Now search the index:
        IndexSearcher isearcher = new IndexSearcher(directory);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query query = parser.parse("text");
        Hits hits = isearcher.search(query);
        Assert.assertEquals(1, hits.length());
        // Iterate through the results:
        for (int i = 0; i < hits.length(); i++) {
            Document hitDoc = hits.doc(i);
            Assert.assertEquals(text, hitDoc.get("fieldname"));
        }
        isearcher.close();
        directory.close();
    }

}
