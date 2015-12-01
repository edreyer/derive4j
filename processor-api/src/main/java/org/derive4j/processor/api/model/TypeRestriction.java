/*
 * Copyright (c) 2015, Jean-Baptiste Giraudeau <jb@giraudeau.info>
 *
 * This file is part of "Derive4J - Processor API".
 *
 * "Derive4J - Processor API" is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * "Derive4J - Processor API" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "Derive4J - Processor API".  If not, see <http://www.gnu.org/licenses/>.
 */
package org.derive4j.processor.api.model;

import org.derive4j.Data;
import org.derive4j.Derive;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;

import static org.derive4j.Visibility.Smart;
import static org.derive4j.processor.api.model.TypeRestrictions.*;

@Data(@Derive(withVisibility = Smart))
public abstract class TypeRestriction {

  TypeRestriction() {
  }

  public static TypeRestriction typeRestriction(TypeVariable restrictedTypeVariable, TypeMirror type, DataArgument idFunction) {
    return TypeRestrictions.typeRestriction(restrictedTypeVariable, type, idFunction);
  }

  public TypeVariable restrictedTypeVariable() {
    return getRestrictedTypeVariable(this);
  }

  public TypeMirror refinementType() {
    return getRefinementType(this);
  }

  public DataArgument idFunction() {
    return getIdFunction(this);
  }

  public abstract <R> R match(Case<R> typeRestriction);

  public interface Case<R> {
    R typeRestriction(TypeVariable restrictedTypeVariable, TypeMirror refinementType, DataArgument idFunction);
  }

}
