package com.acueducto.arpa;

import com.acueducto.arpa.domain.service.ArticleRecordService;
import com.acueducto.arpa.infrastructure.adapter.persistence.JpaArticleRecordRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.JpaArticleTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.JpaIdentificationTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.JpaPersonTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ArticleRecordServiceTest {
    @Mock
    private JpaArticleRecordRepository articleRecordRepository;
    @Mock
    private JpaIdentificationTypeRepository identificationTypeRepository;
    @Mock
    private JpaPersonTypeRepository personTypeRepository;
    @Mock
    private JpaArticleTypeRepository articleTypeRepository;

    @InjectMocks
    private ArticleRecordService articleRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   /* @Test
    void testRegisterEntry() {
        IdentificationType identificationType = new IdentificationType();
        identificationType.setId(1L);
        identificationType.setName("Citizenship Card");
        PersonType personType = new PersonType();
        personType.setId(1L);
        personType.setName("Employee");
        ArticleType articleType = new ArticleType();
        articleType.setId(1L);
        articleType.setName("PC");

        when(identificationTypeRepository.findById(1L)).thenReturn(Optional.of(identificationType));
        when(personTypeRepository.findById(1L)).thenReturn(Optional.of(personType));
        when(articleTypeRepository.findById(1L)).thenReturn(Optional.of(articleType));
        when(articleRecordRepository.save(any(ArticleRecord.class))).thenAnswer(i -> i.getArgument(0));

        ArticleRecord article = articleRecordService.registerEntry(1L, 1L, 1L, "John", "HP", "1234", "Comment");

        assertNotNull(article);
        assertEquals(new Name("John"), article.getName());
        assertEquals(new Brand("HP"), article.getBrand());
        assertEquals(new Serial("1234"), article.getSerial());
        assertEquals(new Comment("Comment"), article.getComment());
        assertEquals(ArticleRecord.ArticleStatus.ENTRY, article.getStatus());
        assertNotNull(article.getEntryDate());
        assertNull(article.getExitDate());
    }

    @Test
    void testRegisterExit() {
        ArticleRecord article = new ArticleRecord();
        article.setId(1L);
        article.setStatus(ArticleRecord.ArticleStatus.ENTRY);

        when(articleRecordRepository.findById(1L)).thenReturn(Optional.of(article));
        when(articleRecordRepository.save(any(ArticleRecord.class))).thenAnswer(i -> i.getArgument(0));

        ArticleRecord updated = articleRecordService.registerExit(1L);
        assertEquals(ArticleRecord.ArticleStatus.EXIT, updated.getStatus());
        assertNotNull(updated.getExitDate());
    }

    @Test
    void testRegisterExitAlreadyExited() {
        ArticleRecord article = new ArticleRecord();
        article.setId(1L);
        article.setStatus(ArticleRecord.ArticleStatus.EXIT);

        when(articleRecordRepository.findById(1L)).thenReturn(Optional.of(article));

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            articleRecordService.registerExit(1L);
        });
        assertEquals("Article has already left", ex.getMessage());
    }*/
} 