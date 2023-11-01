package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        //inputを小文字に変換
        var lowerinput = input.toLowerCase(Locale.ROOT);
        //inputとproduct_nameかdescriptionが一致するstringクエリ文を生成
        var query = "SELECT * FROM product WHERE LOWER(product_name) LIKE '%" + lowerinput + "%' OR LOWER(description) LIKE '%" + lowerinput + "%'";
        //nativeQueryを使ってクエリを実行して結果を返す
        return em.createNativeQuery(query, Product.class).getResultList();
    }

}
