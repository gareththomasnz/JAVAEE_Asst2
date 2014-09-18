import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class IndexWriterTest {
	public static void main(String[] args) {
		IndexWriterTest writer = new IndexWriterTest();
	}

	public IndexWriterTest() {
		try {

			CharArraySet stopwords = new CharArraySet(new ArrayList(), true /*
			 * ignore
			 * case
			 */);
			BufferedReader sw = new BufferedReader(new FileReader(
					LuceneIndexConstants.ENGLISH_STOP));

			/* read in the stop words file from the Lucene distribution */

			while (true) {
				String temp = sw.readLine();
				if (temp != null) {
					stopwords.add(temp);
				} else
					break;
			}
			sw.close();

			Analyzer analyzer = new EnglishAnalyzer(stopwords);

			IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST,
					analyzer);
			SimpleFSDirectory simpleFSDirectory = new SimpleFSDirectory(
					new File(LuceneIndexConstants.LUCENE_DATABASE));

			IndexWriter writer = new IndexWriter(simpleFSDirectory, conf);

			File directory = new File("data/");
			String[] books = directory.list();

			for (int i = 0; i < books.length; i++) {
				BufferedReader br = new BufferedReader(new FileReader("data/"
						+ books[i]));
				System.out.println(books[i]);

				StringBuilder data = new StringBuilder();

				while (true) {
					int character = br.read();

					if (character == -1) {
						break;
					} else {
						data.append((char) character);
					}
				}

				br.close();

				Document doc = new Document();
				doc.add(new TextField("content", data.toString(), Store.YES));
				doc.add(new StoredField("name", books[i]));

				writer.addDocument(doc);
			}
			writer.close();

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
