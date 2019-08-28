import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestLucene {
    private static void showSearchResults(IndexSearcher searcher, ScoreDoc[] hits, Query query, SmartChineseAnalyzer analyzer)
            throws Exception {
        System.out.println("找到 " + hits.length + " 个命中.");
        System.out.println("序号\t匹配度得分\t结果");
        for (int i = 0; i < hits.length; ++i) {
            ScoreDoc scoreDoc = hits[i];
            int docId = scoreDoc.doc;
            Document d = searcher.doc(docId);
            List<IndexableField> fields = d.getFields();
            System.out.print((i + 1));
            System.out.print("\t" + scoreDoc.score);
            for (IndexableField f : fields) {
                System.out.print("\t" + d.get(f.name()));
            }
            System.out.println();
        }
    }

    private static Directory createIndex(SmartChineseAnalyzer analyzer, List<String> products) throws IOException {
        //noinspection deprecation
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        try (IndexWriter writer = new IndexWriter(index, config)) {
            for (String name : products) {
                Document doc = new Document();
                doc.add(new TextField("name", name, Field.Store.YES));
                writer.addDocument(doc);
            }
        }
        return index;
    }

    @Test
    public void TestDemo() throws Exception {
        // 1. 准备中文分词器

        //SmartChineseAnalyzer
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

        // 2. 索引
        List<String> productNames = new ArrayList<>();
        productNames.add("飞利浦led灯泡e27螺口暖白球泡灯家用照明超亮节能灯泡转色温灯泡");
        productNames.add("飞利浦led灯泡e14螺口蜡烛灯泡3W尖泡拉尾节能灯泡暖黄光源Lamp");
        productNames.add("雷士照明 LED灯泡 e27大螺口节能灯3W球泡灯 Lamp led节能灯泡");
        productNames.add("飞利浦 led灯泡 e27螺口家用3w暖白球泡灯节能灯5W灯泡LED单灯7w");
        productNames.add("飞利浦led小球泡e14螺口4.5w透明款led节能灯泡照明光源lamp单灯");
        productNames.add("飞利浦蒲公英护眼台灯工作学习阅读节能灯具30508带光源");
        productNames.add("欧普照明led灯泡蜡烛节能灯泡e14螺口球泡灯超亮照明单灯光源");
        productNames.add("欧普照明led灯泡节能灯泡超亮光源e14e27螺旋螺口小球泡暖黄家用");
        productNames.add("聚欧普照明led灯泡节能灯泡e27螺口球泡家用led照明单灯超亮光源");
        Directory index = createIndex(analyzer, productNames);

        // 3. 查询器
        String keyword = "护眼带光源";
        Query query = new QueryParser("name", analyzer).parse(keyword);

        // 4. 搜索
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int numberPerPage = 1000;
        System.out.printf("当前一共有%d条数据%n", productNames.size());
        System.out.printf("查询关键字是：\"%s\"%n", keyword);
        ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;

        // 5. 显示查询结果
        showSearchResults(searcher, hits, query, analyzer);
        // 6. 关闭查询
        reader.close();
    }
}
