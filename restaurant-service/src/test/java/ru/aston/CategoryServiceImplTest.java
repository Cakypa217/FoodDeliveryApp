package ru.aston;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aston.mapper.CategoryMapper;
import ru.aston.model.dto.CategoryDto;
import ru.aston.model.entity.Category;
import ru.aston.repository.CategoryRepository;
import ru.aston.service.category.CategoryServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCategory_shouldSaveAndReturnDto() {
        CategoryDto dto = new CategoryDto(null, "Pizza");
        Category entity = new Category();
        entity.setName("Pizza");

        Category savedEntity = new Category();
        savedEntity.setId(1L);
        savedEntity.setName("Pizza");

        CategoryDto savedDto = new CategoryDto(1L, "Pizza");

        when(categoryMapper.toEntity(dto)).thenReturn(entity);
        when(categoryRepository.save(entity)).thenReturn(savedEntity);
        when(categoryMapper.toDto(savedEntity)).thenReturn(savedDto);

        CategoryDto result = categoryService.addCategory(dto);

        assertThat(result).isEqualTo(savedDto);
        verify(categoryRepository).save(entity);
    }

    @Test
    void getAllCategories_shouldReturnListOfDtos() {
        Category entity1 = new Category();
        entity1.setId(1L);
        entity1.setName("Pizza");

        Category entity2 = new Category();
        entity2.setId(2L);
        entity2.setName("Pasta");

        List<Category> entities = Arrays.asList(entity1, entity2);

        CategoryDto dto1 = new CategoryDto(1L, "Pizza");
        CategoryDto dto2 = new CategoryDto(2L, "Pasta");

        when(categoryRepository.findAll()).thenReturn(entities);
        when(categoryMapper.toDto(entity1)).thenReturn(dto1);
        when(categoryMapper.toDto(entity2)).thenReturn(dto2);

        List<CategoryDto> result = categoryService.getAllCategories();

        assertThat(result).containsExactly(dto1, dto2);
        verify(categoryRepository).findAll();
    }

    @Test
    void deleteCategory_shouldCallRepositoryDelete() {
        Long id = 1L;
        categoryService.deleteCategory(id);
        verify(categoryRepository).deleteById(id);
    }
}
