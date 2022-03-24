import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllVocabularyComponent } from './all-vocabulary.component';

describe('AllVocabularyComponent', () => {
  let component: AllVocabularyComponent;
  let fixture: ComponentFixture<AllVocabularyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllVocabularyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllVocabularyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
