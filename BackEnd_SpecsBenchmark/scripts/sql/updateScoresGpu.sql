UPDATE gpus
SET
  compute_score =
    0.6 * COALESCE(single_float_gflops, 0)
  + 0.4 * COALESCE(memory_bandwidth_gbs, 0),

  compute_efficiency =
    (
      (
        0.6 * COALESCE(single_float_gflops, 0)
      + 0.4 * COALESCE(memory_bandwidth_gbs, 0)
      )
      / COALESCE(NULLIF(tdpw, 0), 1)
    ) * 100,

  gaming_score =
    (
      (
        (COALESCE(single_float_gflops, 0) * 0.60) +
        (COALESCE(pixel_rate_gpixels, 0) * 5.0) +
        (COALESCE(memory_bandwidth_gbs, 0) * 0.15)
      )
      *
      CASE
        WHEN lower(COALESCE(architecture, '')) LIKE '%ada lovelace%'
          OR lower(COALESCE(architecture, '')) LIKE '%blackwell%'
          OR lower(COALESCE(architecture, '')) LIKE '%rdna 4%'
          OR lower(COALESCE(architecture, '')) LIKE '%xe2%' THEN 1.35
        WHEN lower(COALESCE(architecture, '')) LIKE '%ampere%'
          OR lower(COALESCE(architecture, '')) LIKE '%rdna 3%' THEN 1.35
        WHEN lower(COALESCE(architecture, '')) LIKE '%turing%'
          OR lower(COALESCE(architecture, '')) LIKE '%rdna 2%' THEN 1.2
        WHEN lower(COALESCE(architecture, '')) LIKE '%pascal%'
          OR lower(COALESCE(architecture, '')) LIKE '%rdna 1%' THEN 1.0
        WHEN lower(COALESCE(architecture, '')) LIKE '%maxwell%'
          OR lower(COALESCE(architecture, '')) LIKE '%vega%' THEN 0.95
        ELSE 0.95
      END
    )
    +
    (COALESCE(rt_cores, 0) * 1.5) + (COALESCE(tensor_cores, 0) * 0.5)

    -- IMPORTANT: adjust memory borders if necessary
    *
    CASE
      WHEN COALESCE(memory_size_gb, 0) > 0 AND COALESCE(memory_size_gb, 0) < 4 THEN 0.7
      WHEN COALESCE(memory_size_gb, 0) > 4 AND COALESCE(memory_size_gb, 0) < 8 THEN 0.85
      WHEN COALESCE(memory_size_gb, 0) >= 12 THEN 1.1
      ELSE 1.0
    END,

  gaming_efficiency =
    (
      (
        (
          (
            (COALESCE(single_float_gflops, 0) * 0.60) +
            (COALESCE(pixel_rate_gpixels, 0) * 5.0) +
            (COALESCE(memory_bandwidth_gbs, 0) * 0.15)
          )
          *
          CASE
            WHEN lower(COALESCE(architecture, '')) LIKE '%ada lovelace%'
              OR lower(COALESCE(architecture, '')) LIKE '%blackwell%'
              OR lower(COALESCE(architecture, '')) LIKE '%rdna 4%'
              OR lower(COALESCE(architecture, '')) LIKE '%xe2%' THEN 1.35
            WHEN lower(COALESCE(architecture, '')) LIKE '%ampere%'
              OR lower(COALESCE(architecture, '')) LIKE '%rdna 3%' THEN 1.35
            WHEN lower(COALESCE(architecture, '')) LIKE '%turing%'
              OR lower(COALESCE(architecture, '')) LIKE '%rdna 2%' THEN 1.2
            WHEN lower(COALESCE(architecture, '')) LIKE '%pascal%'
              OR lower(COALESCE(architecture, '')) LIKE '%rdna 1%' THEN 1.0
            WHEN lower(COALESCE(architecture, '')) LIKE '%maxwell%'
              OR lower(COALESCE(architecture, '')) LIKE '%vega%' THEN 0.95
            ELSE 0.95
          END
        )
        +
        (COALESCE(rt_cores, 0) * 1.5) + (COALESCE(tensor_cores, 0) * 0.5)
      )
      -- IMPORTANT: adjust memory borders if necessary
      *
      CASE
        WHEN COALESCE(memory_size_gb, 0) > 0 AND COALESCE(memory_size_gb, 0) < 6 THEN 0.8
        WHEN COALESCE(memory_size_gb, 0) > 4 AND COALESCE(memory_size_gb, 0) < 8 THEN 0.85
        WHEN COALESCE(memory_size_gb, 0) >= 12 THEN 1.05
        ELSE 1.0
      END
      / COALESCE(NULLIF(tdpw, 0), 1)
    ) * 100;
