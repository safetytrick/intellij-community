package com.intellij.json.psi.impl;

import com.intellij.json.psi.JsonElementGenerator;
import com.intellij.json.psi.JsonProperty;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mikhail Golubev
 */
abstract class JsonPropertyMixin extends JsonElementImpl implements JsonProperty {
  public JsonPropertyMixin(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
    final JsonElementGenerator generator = new JsonElementGenerator(getProject());
    // Strip only both quotes in case user wants some exotic name like key'
    return getNameElement().replace(generator.createStringLiteral(StringUtil.unquoteString(name)));
  }

  @Override
  public PsiReference getReference() {
    return new JsonNamePropertyReference(this);
  }
}
