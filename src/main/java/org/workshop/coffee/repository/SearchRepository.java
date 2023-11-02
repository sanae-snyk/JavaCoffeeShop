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
        var lowerInput = input.toLowerCase(Locale.ROOT);
        //inputとproduct_nameとdescriptionに一致する名前付きのパラメーターを持つSQLクエリを作成
        var query = "SELECT * FROM product WHERE LOWER(product_name) LIKE :input OR LOWER(description) LIKE :input";

        //nativeQueryを使ってクエリを実行
            return em.createNativeQuery(query, Product.class)
                .setParameter("input", "%" + lowerInput + "%")
                .getResultList();
    }

}
