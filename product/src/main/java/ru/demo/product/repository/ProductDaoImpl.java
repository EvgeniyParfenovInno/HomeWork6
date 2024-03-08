package ru.demo.product.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.demo.product.entity.ProductEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private static final String ID_FIELD = "id";
    private static final String ACCOUNT_FIELD = "account";
    private static final String BALANCE_FIELD = "balance";
    private static final String TYPE_FIELD = "type";
    private static final String USER_ID_FIELD = "userId";

    private static final String GET_BY_ID_SQL_REQUEST = "select * from products where id=?";
    private static final String GET_BY_USER_ID_SQL_REQUEST = "select * from products where userId=?";

    private final DataSource dataSource;

    @Override
    public Optional<ProductEntity> getById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID_SQL_REQUEST)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapToEntity(rs));
            }
            return Optional.empty();
        }
    }

    @Override
    public List<ProductEntity> getByUserId(Long userId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_USER_ID_SQL_REQUEST)) {
            ps.setLong(1, userId);
            List<ProductEntity> products = new LinkedList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(mapToEntity(rs));
            }
            return products;
        }
    }

    private ProductEntity mapToEntity(ResultSet rs) throws SQLException {
        return new ProductEntity()
                .setId(rs.getLong(ID_FIELD))
                .setAccount(rs.getString(ACCOUNT_FIELD))
                .setBalance(rs.getBigDecimal(BALANCE_FIELD))
                .setType(rs.getInt(TYPE_FIELD))
                .setUserId(rs.getLong(USER_ID_FIELD));
    }
}
