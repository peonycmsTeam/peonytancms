package com.peony.peonyfront.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LuceneUtil {
    private static final Logger   log      = LoggerFactory.getLogger(LuceneUtil.class);
    public static        Analyzer analyzer = new IKAnalyzer(true);

    public IndexSearcher getIndexSearcher(String userId) {
        IndexReader reader = null;
        IndexSearcher searcher = null;
        IndexReader[] indexReader = null;
        try {
            File file = new File("/home/index/" + userId);
            File[] flist = file.listFiles();
            Directory[] dir = new Directory[flist.length];
            if (reader == null) {
                List<IndexReader> list = new ArrayList();

                for (int i = 0; i < flist.length; i++) {
                    if (flist[i].exists()) {
                        dir[i] = FSDirectory.open(flist[i]);
                        list.add(IndexReader.open(dir[i]));
                    }

                }
                indexReader = new IndexReader[list.size()];
                for (int j = 0; j < list.size(); j++) {
                    indexReader[j] = list.get(j);
                }

                reader = new MultiReader(indexReader);
            } else {
                if (!reader.isCurrent()) {
                    reader = reader.reopen();
                }
            }

            if (searcher == null) {
                searcher = new IndexSearcher(reader);
            } else {
                IndexReader r = searcher.getIndexReader();
                if (!r.isCurrent()) {
                    searcher = new IndexSearcher(reader);
                }
            }
            return searcher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public IndexReader getIndexReader(String userId) throws CorruptIndexException, IOException {
        IndexReader reader = null;
        IndexSearcher searcher = null;
        IndexReader[] indexReader = null;
        File file = new File("/home/index/" + userId);
        File[] flist = file.listFiles();
        Directory[] dir = new Directory[flist.length];
        if (reader == null) {
            List<IndexReader> list = new ArrayList();

            for (int i = 0; i < flist.length; i++) {
                if (flist[i].exists()) {
                    dir[i] = FSDirectory.open(flist[i]);
                    list.add(IndexReader.open(dir[i]));
                }

            }
            indexReader = new IndexReader[list.size()];
            for (int j = 0; j < list.size(); j++) {
                indexReader[j] = list.get(j);
            }

            reader = new MultiReader(indexReader);
        } else {
            if (!reader.isCurrent()) {
                reader = reader.reopen();
            }
        }
        return reader;
    }

    public void close(IndexReader indexReader) {
        try {
            indexReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}