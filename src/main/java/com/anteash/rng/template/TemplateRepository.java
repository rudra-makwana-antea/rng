package com.anteash.rng.template;

import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<Template, Long> {
    Template findByName(String name);

    long deleteByName(String templateName);
}
