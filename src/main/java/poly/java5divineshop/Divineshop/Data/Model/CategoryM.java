package poly.java5divineshop.Divineshop.Data.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.java5divineshop.Divineshop.Data.Dto.CategoryDTO;
import poly.java5divineshop.Divineshop.Data.Entity.CategoryE;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryM {

    private int id;
    private String tenTheLoai;
    private List<ProductM> products;

    public static CategoryM convertCategoryEToCategoryM(CategoryE categoryE) {
        return CategoryM.builder()
                .id(categoryE.getId())
                .tenTheLoai(categoryE.getTenTheLoai())
//                .products(ProductM.convertListProductEToListProductM(categoryE.getProducts()))
                .build();
    }

    public static CategoryM convertCategoryDTOToCategoryM(CategoryDTO categoryDTO) {
        return CategoryM.builder()
                .id(categoryDTO.getId())
                .tenTheLoai(categoryDTO.getTenTheLoai())
//                .products(ProductM.convertListProductDTOToListProductM(categoryDTO.getProducts()))
                .build();
    }

    public static CategoryDTO convertCategoryMToCategoryDTO(CategoryM categoryM) {
        return CategoryDTO.builder()
                .id(categoryM.getId())
                .tenTheLoai(categoryM.getTenTheLoai())
//                .products(ProductM.convertListProductMToListProductDTO(categoryM.getProducts()))
                .build();
    }

    public static CategoryE convertCategoryMToCategoryE(CategoryM categoryM) {
        return CategoryE.builder()
                .id(categoryM.getId())
                .tenTheLoai(categoryM.getTenTheLoai())
//                .products(ProductM.convertListProductMToListProductE(categoryM.getProducts()))
                .build();
    }

    public static List<CategoryM> convertListCategoryEToListCategoryM(List<CategoryE> categoryEList) {
        return categoryEList.stream()
                .map(CategoryM::convertCategoryEToCategoryM)
                .collect(Collectors.toList());
    }

    public static List<CategoryM> convertListCategoryDTOToListCategoryM(List<CategoryDTO> categoryDTOList) {
        return categoryDTOList.stream()
                .map(CategoryM::convertCategoryDTOToCategoryM)
                .collect(Collectors.toList());
    }

    public static List<CategoryDTO> convertListCategoryMToListCategoryDTO(List<CategoryM> categoryMList) {
        return categoryMList.stream()
                .map(CategoryM::convertCategoryMToCategoryDTO)
                .collect(Collectors.toList());
    }

    public static List<CategoryE> convertListCategoryMToListCategoryE(List<CategoryM> categoryMList) {
        return categoryMList.stream()
                .map(CategoryM::convertCategoryMToCategoryE)
                .collect(Collectors.toList());
    }

}
