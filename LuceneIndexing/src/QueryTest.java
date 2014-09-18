import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.QueryBuilder;

public class QueryTest {
	public static void main(String[] args) {
		CharArraySet stopwords = new CharArraySet(new ArrayList(), true /*
		 * ignore
		 * case
		 */);
		BufferedReader sw;
		try {
			sw = new BufferedReader(new FileReader(LuceneIndexConstants.ENGLISH_STOP));

			/* read in the stop words file from the lucene distribution */

			while (true) {
				String temp = sw.readLine();
				if (temp != null) {
					stopwords.add(temp);
				} else
					break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Analyzer analyzer = new EnglishAnalyzer(stopwords);

		String querystr = "Alice";
//		String querystr = "\"Alice\'s Adventures in Wonderland\"";
		try {
			QueryBuilder builder = new QueryBuilder(analyzer);
			Query q = builder.createPhraseQuery("content", querystr);

			int hitsPerPage = 10;
			SimpleFSDirectory simpleFSDirectory = new SimpleFSDirectory(
					new File(LuceneIndexConstants.LUCENE_DATABASE));
			IndexReader reader = DirectoryReader.open(simpleFSDirectory);

			IndexSearcher searcher = new IndexSearcher(reader);

			TopScoreDocCollector collector = TopScoreDocCollector.create(
					hitsPerPage, true);

			searcher.search(q, collector);

			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			System.out.println("Found " + hits.length + " hits.");
			for (int i = 0; i < hits.length; ++i) {
				ScoreDoc hit = hits[i];
				int docId = hit.doc;
				Document d = searcher.doc(docId);
				System.out.println((i + 1) + ". " + d.get("name") + " "
						+ hit.score);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
