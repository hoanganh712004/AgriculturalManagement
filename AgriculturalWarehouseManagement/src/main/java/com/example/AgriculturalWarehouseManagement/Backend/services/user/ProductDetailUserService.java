package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.*;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailUserService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    // Get list weight
    public List<WeightCompareProductDetailsResponse> getcompareProductDetail(Integer productId) {
        List<Object[]> raw = productDetailRepository.rawGetWeightCompareProductDetails(productId);

        return raw.stream().map(row -> new WeightCompareProductDetailsResponse(
                ((Number) row[0]).intValue(),       // productDetailId
                ((Number) row[1]).intValue(),       // productId
                ((Number) row[2]).intValue(),   // categoryWeightId
                ((Number) row[3]).intValue(),   // categoryId
                ((Number) row[4]).intValue(),   // expiry
                ((Number) row[5]).doubleValue(), //price
                (String) row[6]           // productWeight (weight + unit)
        )).toList();
    }


    public List<WeightCompareProductDetailsResponse> getcompareProductDetails(Integer productId) {
        if (getcompareProductDetail(productId).isEmpty()) {
            return new ArrayList<>();
        } else {
            List<WeightCompareProductDetailsResponse> response = getcompareProductDetail(productId);

            return response;
        }
    }


    // Get productDetails
    public List<ProductDetailUserResponse> getProductDetailUser(Integer productDetaiId) {
        List<Object[]> raw = productDetailRepository.rawGetProductDetails(productDetaiId);

        return raw.stream().map(row -> new ProductDetailUserResponse(
                ((Number) row[0]).intValue(),                // categoryId
                ((Number) row[1]).intValue(),               // productId
                (String) row[2],                            // productName
                (String) row[3],                            // productDescription
                (String) row[4],                            // imageUrl
                ((Number) row[5]).intValue(),               // productDetailId
                (String) row[6],                            // productWeight
                ((Number) row[7]).intValue(),               // batchId
                ((Number) row[8]).intValue(),               // importedQuantity
                ((Number) row[9]).intValue(),               // soldQuantity
                ((java.sql.Date) row[10]).toLocalDate(),     // manufactureDate
                ((Number) row[11]).intValue(),              // expiry
                ((Number) row[12]).doubleValue(),              // Price
                ((java.sql.Date) row[13]).toLocalDate(),    // expiryDate
                ((Number) row[14]).intValue(),              // totalAdjustedRemoveQuantity
                ((Number) row[15]).intValue(),              // remainQuantity
                (String) row[16],                           // status
                (String) row[17],                            // expiryStatus
                ((Number) row[18]).intValue(),                  // avgRating
                ((Number) row[19]).intValue()                   // ratingCount
        )).toList();

    }

    public ProductDetailUserResponse getProductDetailUsers(Integer productDetaiId) {
        if (getProductDetailUser(productDetaiId).isEmpty()) {
            return new ProductDetailUserResponse();
        } else {
            List<ProductDetailUserResponse> response = getProductDetailUser(productDetaiId);
            return response.get(0);
        }
    }

    // Get productdetail by productId
    public List<ProductDetailUserResponse> getProductUser(Integer productId) {
        List<Object[]> raw = productDetailRepository.rawGetProductbyIds(productId);

        return raw.stream().map(row -> new ProductDetailUserResponse(
                ((Number) row[0]).intValue(),                // categoryId
                ((Number) row[1]).intValue(),               // productId
                (String) row[2],                            // productName
                (String) row[3],                            // productDescription
                (String) row[4],                            // imageUrl
                ((Number) row[5]).intValue(),               // productDetailId
                (String) row[6],                            // productWeight
                ((Number) row[7]).intValue(),               // batchId
                ((Number) row[8]).intValue(),               // importedQuantity
                ((Number) row[9]).intValue(),               // soldQuantity
                ((java.sql.Date) row[10]).toLocalDate(),     // manufactureDate
                ((Number) row[11]).intValue(),              // expiry
                ((Number) row[12]).doubleValue(),              // Price
                ((java.sql.Date) row[13]).toLocalDate(),    // expiryDate
                ((Number) row[14]).intValue(),              // totalAdjustedRemoveQuantity
                ((Number) row[15]).intValue(),              // remainQuantity
                (String) row[16],                           // status
                (String) row[17],                            // expiryStatus
                ((Number) row[18]).intValue(),                  // avgRating
                ((Number) row[19]).intValue()                   // ratingCount
        )).toList();

    }

    public List<ProductDetailUserResponse> getProductUsers(Integer productId) {
        if (getProductUser(productId).isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ProductDetailUserResponse> response = getProductUser(productId);

            return response;
        }
    }

    // Get trending product sort soldquantity
    public List<ProductDetailUserResponse> getTrendingProduct() {
        List<Object[]> raw = productDetailRepository.rawgetTrendingProduct();

        return raw.stream().map(row -> new ProductDetailUserResponse(
                ((Number) row[0]).intValue(),                // categoryId
                ((Number) row[1]).intValue(),               // productId
                (String) row[2],                            // productName
                (String) row[3],                            // productDescription
                (String) row[4],                            // imageUrl
                ((Number) row[5]).intValue(),               // productDetailId
                (String) row[6],                            // productWeight
                ((Number) row[7]).intValue(),               // batchId
                ((Number) row[8]).intValue(),               // importedQuantity
                ((Number) row[9]).intValue(),               // soldQuantity
                ((java.sql.Date) row[10]).toLocalDate(),     // manufactureDate
                ((Number) row[11]).intValue(),              // expiry
                ((Number) row[12]).doubleValue(),              // Price
                ((java.sql.Date) row[13]).toLocalDate(),    // expiryDate
                ((Number) row[14]).intValue(),              // totalAdjustedRemoveQuantity
                ((Number) row[15]).intValue(),              // remainQuantity
                (String) row[16],                           // status
                (String) row[17],                            // expiryStatus
                ((Number) row[18]).intValue(),                  // avgRating
                ((Number) row[19]).intValue()                   // ratingCount
        )).toList();

    }

    public List<ProductDetailUserResponse> getTrendingProducts() {

        if (getTrendingProduct().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ProductDetailUserResponse> response = getTrendingProduct();
            return response;
        }
    }

    public ResponseResult<ProductDetailUserResponse> checkQuantityProduct(int quantity, int productDetailId) {
        ProductDetailUserResponse productDetailUserResponse = getProductDetailUsers(productDetailId);

        if (productDetailUserResponse.getStatus().equals("Hết hàng")) {
            return new ResponseResult<>("ERROR", "Sản phẩm này hiện không còn hàng. Vui lòng chọn sản phẩm khác.",false);
        }

        if (productDetailUserResponse.getExpiryStatus().equals("Hết hạn")){
            return new ResponseResult<>("ERROR", "Sản phẩm này hiện hết hạn. Vui lòng chọn sản phẩm khác",false);
        }

        if (quantity > productDetailUserResponse.getRemainQuantity()) {
            return new ResponseResult<>("ERROR","Số lượng bạn nhập không được lớn hớn số lượng của Shop",false);
        }

        return new ResponseResult<>("SUCCESS", "Thêm vào giỏ hàn thành công", true);
    }
}
