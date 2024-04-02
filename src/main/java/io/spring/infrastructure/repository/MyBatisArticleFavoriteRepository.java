package io.spring.infrastructure.repository;

import io.spring.core.favorite.ArticleFavorite;
import io.spring.core.favorite.ArticleFavoriteRepository;
import io.spring.infrastructure.mybatis.mapper.ArticleFavoriteMapper;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MyBatisArticleFavoriteRepository implements ArticleFavoriteRepository {
  private ArticleFavoriteMapper mapper;

  public MyBatisArticleFavoriteRepository(ArticleFavoriteMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public void save(ArticleFavorite articleFavorite) {
    if (mapper.find(articleFavorite.getArticleId(), articleFavorite.getUserId()) == null) {
      mapper.insert(articleFavorite);
    }
  }

  @Override
  public Optional<ArticleFavorite> find(String articleId, String userId) {
    return Optional.ofNullable(mapper.find(articleId, userId));
  }

  @Override
  public void remove(ArticleFavorite favorite) {
    mapper.delete(favorite);
  }
}
