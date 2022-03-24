import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindVocabularyComponent } from './find-vocabulary.component';

describe('FindVocabularyComponent', () => {
  let component: FindVocabularyComponent;
  let fixture: ComponentFixture<FindVocabularyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindVocabularyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindVocabularyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
